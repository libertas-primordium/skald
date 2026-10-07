package com.libertasprimordium.skald.security

private const val UINT16_MAX = 0xffff
private const val VAULT_ID_BYTES = 16
private const val RECORD_ID_BYTES = 16
private const val HEADER_COMMITMENT_TAG_BYTES = 32
private const val MAX_CONTAINER_METADATA_BYTES = 1024
private const val MAX_RECORD_METADATA_BYTES = 1024
private const val MAX_RECORD_CIPHERTEXT_BYTES = 4096
private const val MAX_RECORD_REFERENCE_BYTES = 128
private const val MAX_RECORDS = 8

enum class SkaldVaultV1ContainerRejectionReason(val label: String) {
    UnsupportedMagic("unsupported vault container magic"),
    UnsupportedVersion("unsupported vault container version"),
    UnsupportedSuiteId("unsupported provider suite id"),
    UnsupportedKdfAlgorithm("unsupported KDF algorithm"),
    UnsupportedKdfVersion("unsupported KDF version"),
    UnsupportedKdfParameters("unsupported KDF parameters"),
    UnsupportedPolicyId("unsupported policy id"),
    UnsupportedRecordType("unsupported record type"),
    MalformedLength("malformed length"),
    MalformedIntegerValue("malformed integer value"),
    MalformedStringValue("malformed string value"),
    MalformedRecordReference("malformed record reference"),
    MalformedManifestSection("malformed manifest section"),
    TruncatedInput("truncated input"),
    TrailingBytes("trailing bytes"),
    UnknownField("unknown field in non-extensible section"),
    DuplicatedField("duplicated field in non-extensible section"),
    NonCanonicalHeaderEvidence("non-canonical header evidence"),
}

sealed class SkaldVaultV1ContainerResult<out T> {
    data class Accepted<out T>(val value: T) : SkaldVaultV1ContainerResult<T>()

    data class Rejected(
        val reason: SkaldVaultV1ContainerRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1ContainerResult<Nothing>()
}

data class SkaldVaultV1ContainerRecordEntry(
    val recordTypeId: String,
    val recordId: ByteArray,
    val recordVersionCounter: Long,
    val ciphertext: ByteArray,
    val integrityCriticalRecordMetadata: ByteArray,
    val recordReference: String,
)

data class SkaldVaultV1ContainerManifestRecordState(
    val recordId: ByteArray,
    val recordTypeId: String,
    val latestRecordVersionCounter: Long,
    val recordReference: String,
    val tombstone: Boolean,
)

data class SkaldVaultV1ContainerManifestSection(
    val manifestMagic: String = SkaldVaultV1ContainerFormat.MANIFEST_MAGIC,
    val manifestPolicyId: String = SkaldVaultV1ContainerFormat.MANIFEST_POLICY_ID,
    val manifestPolicyVersion: Int = SkaldVaultV1ContainerFormat.MANIFEST_POLICY_VERSION,
    val vaultId: ByteArray,
    val providerSuiteId: String = SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID,
    val headerCommitmentContext: ByteArray,
    val storageNamespace: String = SkaldVaultV1RecordAead.STORAGE_NAMESPACE,
    val recordNamespace: String = SkaldVaultV1ContainerFormat.RECORD_NAMESPACE,
    val manifestSequence: Long,
    val crashRecoveryMetadata: ByteArray,
    val recordStates: List<SkaldVaultV1ContainerManifestRecordState>,
)

data class SkaldVaultV1Container(
    val vaultMagic: String = SkaldVaultV1HeaderCommitment.VAULT_MAGIC,
    val vaultFormatVersion: Int = SkaldVaultV1HeaderCommitment.VAULT_FORMAT_VERSION,
    val containerPolicyId: String = SkaldVaultV1ContainerFormat.CONTAINER_POLICY_ID,
    val containerPolicyVersion: Int = SkaldVaultV1ContainerFormat.CONTAINER_POLICY_VERSION,
    val canonicalHeader: SkaldVaultV1CanonicalHeader,
    val headerCommitmentTag: ByteArray,
    val manifestPolicyId: String = SkaldVaultV1ContainerFormat.MANIFEST_POLICY_ID,
    val manifestPolicyVersion: Int = SkaldVaultV1ContainerFormat.MANIFEST_POLICY_VERSION,
    val storagePolicyId: String = SkaldVaultV1ContainerFormat.STORAGE_POLICY_ID,
    val storagePolicyVersion: Int = SkaldVaultV1ContainerFormat.STORAGE_POLICY_VERSION,
    val encryptedRecordEntries: List<SkaldVaultV1ContainerRecordEntry>,
    val manifestSection: SkaldVaultV1ContainerManifestSection,
    val preUnlockIntegrityMetadata: ByteArray = ByteArray(0),
) {
    val providerSuiteId: String
        get() = canonicalHeader.providerSuiteId

    val kdfAlgorithmId: String
        get() = canonicalHeader.kdfAlgorithmId

    val kdfVersion: Int
        get() = canonicalHeader.kdfVersion

    val kdfMemoryKiB: Int
        get() = canonicalHeader.kdfMemoryKiB

    val kdfTimeCost: Int
        get() = canonicalHeader.kdfTimeCost

    val kdfParallelism: Int
        get() = canonicalHeader.kdfParallelism

    val salt: ByteArray
        get() = canonicalHeader.salt.copyOf()

    val derivedRootMaterialBytes: Int
        get() = canonicalHeader.derivedRootMaterialBytes

    val vaultId: ByteArray
        get() = canonicalHeader.vaultId.copyOf()

    val passphrasePolicyId: String
        get() = canonicalHeader.passphraseEncodingPolicyId

    val keyExpansionPolicyId: String
        get() = canonicalHeader.keyExpansionPolicyId

    val keySeparationPolicyId: String
        get() = canonicalHeader.keySeparationPolicyId

    val headerCommitmentPrimitivePolicyId: String
        get() = canonicalHeader.headerCommitmentPrimitivePolicyId

    val headerCommitmentPolicyId: String
        get() = canonicalHeader.headerCommitmentPolicyId

    val recordFormatPolicyId: String
        get() = canonicalHeader.recordFormatPolicyId

    val recordFormatPolicyVersion: Int
        get() = canonicalHeader.recordFormatPolicyVersion

    val aadPolicyId: String
        get() = canonicalHeader.aadPolicyId

    val aadPolicyVersion: Int
        get() = canonicalHeader.aadPolicyVersion

    val featureFlags: Int
        get() = canonicalHeader.featureFlags
}

object SkaldVaultV1ContainerFormat {
    const val CONTAINER_POLICY_ID = "skald-vault-v1-container-contract-v1"
    const val CONTAINER_POLICY_VERSION = 1
    const val MANIFEST_MAGIC = "SKALD-VAULT-V1-MANIFEST"
    const val MANIFEST_POLICY_ID = "skald-vault-v1-manifest-contract-v1"
    const val MANIFEST_POLICY_VERSION = 1
    const val STORAGE_POLICY_ID = "skald-vault-v1-local-manifest-storage-policy-v1"
    const val STORAGE_POLICY_VERSION = 1
    const val RECORD_NAMESPACE = "skald-vault/v1/records"

    val FIXTURE_HEADER_COMMITMENT_TAG: ByteArray
        get() = hexToBytes("1d09a657d8929444c1e955410b2dcb3bfc0df2d19b128154e17a5fbd751237d5")

    fun vectorFixtureContainer(): SkaldVaultV1Container {
        val header = SkaldVaultV1HeaderCommitment.vectorFixtureHeader()
        val recordReference = "in-memory-fixture-record-0001"
        val recordEntry = SkaldVaultV1ContainerRecordEntry(
            recordTypeId = SkaldVaultV1RecordType.SensitiveMetadata.typeId,
            recordId = (0x40..0x4f).map { it.toByte() }.toByteArray(),
            recordVersionCounter = 7,
            ciphertext = "fixed non-secret container ciphertext placeholder".encodeToByteArray(),
            integrityCriticalRecordMetadata =
                "skald-vault-v1-container-record-metadata".encodeToByteArray(),
            recordReference = recordReference,
        )
        return SkaldVaultV1Container(
            canonicalHeader = header,
            headerCommitmentTag = FIXTURE_HEADER_COMMITMENT_TAG,
            encryptedRecordEntries = listOf(recordEntry),
            manifestSection = SkaldVaultV1ContainerManifestSection(
                vaultId = header.vaultId,
                headerCommitmentContext = FIXTURE_HEADER_COMMITMENT_TAG,
                manifestSequence = 1,
                crashRecoveryMetadata =
                    "skald-vault-v1-manifest-crash-metadata-fixture".encodeToByteArray(),
                recordStates = listOf(
                    SkaldVaultV1ContainerManifestRecordState(
                        recordId = recordEntry.recordId,
                        recordTypeId = recordEntry.recordTypeId,
                        latestRecordVersionCounter = recordEntry.recordVersionCounter,
                        recordReference = recordReference,
                        tombstone = false,
                    ),
                ),
            ),
            preUnlockIntegrityMetadata = ByteArray(0),
        )
    }

    fun serialize(container: SkaldVaultV1Container): SkaldVaultV1ContainerResult<ByteArray> {
        validateContainer(container)?.let { return rejected(it) }
        val canonicalHeaderBytes = when (
            val result = SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(container.canonicalHeader)
        ) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(mapHeaderRejection(result.reason))
        }
        return accepted(
            byteList {
                putStringField(1, container.vaultMagic)
                putU16Field(2, container.vaultFormatVersion)
                putStringField(3, container.containerPolicyId)
                putU16Field(4, container.containerPolicyVersion)
                putBytesField(5, canonicalHeaderBytes)
                putBytesField(6, container.headerCommitmentTag)
                putStringField(7, container.providerSuiteId)
                putStringField(8, container.kdfAlgorithmId)
                putU16Field(9, container.kdfVersion)
                putU32Field(10, container.kdfMemoryKiB)
                putU32Field(11, container.kdfTimeCost)
                putU32Field(12, container.kdfParallelism)
                putBytesField(13, container.salt)
                putU16Field(14, container.derivedRootMaterialBytes)
                putBytesField(15, container.vaultId)
                putStringField(16, container.passphrasePolicyId)
                putStringField(17, container.keyExpansionPolicyId)
                putStringField(18, container.keySeparationPolicyId)
                putStringField(19, container.headerCommitmentPrimitivePolicyId)
                putStringField(20, container.headerCommitmentPolicyId)
                putStringField(21, container.recordFormatPolicyId)
                putU16Field(22, container.recordFormatPolicyVersion)
                putStringField(23, container.aadPolicyId)
                putU16Field(24, container.aadPolicyVersion)
                putStringField(25, container.manifestPolicyId)
                putU16Field(26, container.manifestPolicyVersion)
                putStringField(27, container.storagePolicyId)
                putU16Field(28, container.storagePolicyVersion)
                putU32Field(29, container.featureFlags)
                putBytesField(30, container.preUnlockIntegrityMetadata)
                putBytesField(31, recordsSectionBytes(container.encryptedRecordEntries))
                putBytesField(32, manifestSectionBytes(container.manifestSection))
            },
        )
    }

    fun parse(bytes: ByteArray): SkaldVaultV1ContainerResult<SkaldVaultV1Container> {
        val reader = ByteReader(bytes)
        val vaultMagic = when (val result = reader.readStringField(1)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val vaultFormatVersion = when (val result = reader.readU16Field(2)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val containerPolicyId = when (val result = reader.readStringField(3)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val containerPolicyVersion = when (val result = reader.readU16Field(4)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val canonicalHeaderBytes = when (val result = reader.readBytesField(5)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val headerCommitmentTag = when (val result = reader.readBytesField(6)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val providerSuiteId = when (val result = reader.readStringField(7)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val kdfAlgorithmId = when (val result = reader.readStringField(8)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val kdfVersion = when (val result = reader.readU16Field(9)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val kdfMemoryKiB = when (val result = reader.readU32Field(10)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val kdfTimeCost = when (val result = reader.readU32Field(11)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val kdfParallelism = when (val result = reader.readU32Field(12)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val salt = when (val result = reader.readBytesField(13)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val derivedRootMaterialBytes = when (val result = reader.readU16Field(14)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val vaultId = when (val result = reader.readBytesField(15)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val passphrasePolicyId = when (val result = reader.readStringField(16)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val keyExpansionPolicyId = when (val result = reader.readStringField(17)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val keySeparationPolicyId = when (val result = reader.readStringField(18)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val headerCommitmentPrimitivePolicyId = when (val result = reader.readStringField(19)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val headerCommitmentPolicyId = when (val result = reader.readStringField(20)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val recordFormatPolicyId = when (val result = reader.readStringField(21)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val recordFormatPolicyVersion = when (val result = reader.readU16Field(22)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val aadPolicyId = when (val result = reader.readStringField(23)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val aadPolicyVersion = when (val result = reader.readU16Field(24)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val manifestPolicyId = when (val result = reader.readStringField(25)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val manifestPolicyVersion = when (val result = reader.readU16Field(26)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val storagePolicyId = when (val result = reader.readStringField(27)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val storagePolicyVersion = when (val result = reader.readU16Field(28)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val featureFlags = when (val result = reader.readU32Field(29)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val preUnlockIntegrityMetadata = when (val result = reader.readBytesField(30)) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val recordEntries = when (val result = parseRecordsSection(reader.readBytesField(31))) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        val manifestSection = when (val result = parseManifestSection(reader.readBytesField(32))) {
            is ReadResult.Ok -> result.value
            is ReadResult.Err -> return rejected(result.reason)
        }
        if (reader.hasRemaining()) {
            return rejected(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
        }
        if (vaultMagic != SkaldVaultV1HeaderCommitment.VAULT_MAGIC) {
            return rejected(SkaldVaultV1ContainerRejectionReason.UnsupportedMagic)
        }

        val header = SkaldVaultV1CanonicalHeader(
            vaultMagic = vaultMagic,
            vaultFormatVersion = vaultFormatVersion,
            providerSuiteId = providerSuiteId,
            kdfAlgorithmId = kdfAlgorithmId,
            kdfVersion = kdfVersion,
            kdfMemoryKiB = kdfMemoryKiB,
            kdfTimeCost = kdfTimeCost,
            kdfParallelism = kdfParallelism,
            salt = salt,
            derivedRootMaterialBytes = derivedRootMaterialBytes,
            vaultId = vaultId,
            passphraseEncodingPolicyId = passphrasePolicyId,
            keyExpansionPolicyId = keyExpansionPolicyId,
            keySeparationPolicyId = keySeparationPolicyId,
            headerCommitmentPrimitivePolicyId = headerCommitmentPrimitivePolicyId,
            headerCommitmentPolicyId = headerCommitmentPolicyId,
            aadPolicyId = aadPolicyId,
            aadPolicyVersion = aadPolicyVersion,
            recordFormatPolicyId = recordFormatPolicyId,
            recordFormatPolicyVersion = recordFormatPolicyVersion,
            featureFlags = featureFlags,
            integrityCriticalHeaderMetadata = preUnlockIntegrityMetadata,
        )
        val reconstructedHeaderBytes = when (val result = SkaldVaultV1HeaderCommitment.canonicalHeaderBytes(header)) {
            is SkaldVaultV1HeaderCommitmentResult.Accepted -> result.value
            is SkaldVaultV1HeaderCommitmentResult.Rejected ->
                return rejected(mapHeaderRejection(result.reason))
        }
        if (!canonicalHeaderBytes.contentEquals(reconstructedHeaderBytes)) {
            return rejected(SkaldVaultV1ContainerRejectionReason.NonCanonicalHeaderEvidence)
        }

        val container = SkaldVaultV1Container(
            vaultMagic = vaultMagic,
            vaultFormatVersion = vaultFormatVersion,
            containerPolicyId = containerPolicyId,
            containerPolicyVersion = containerPolicyVersion,
            canonicalHeader = header,
            headerCommitmentTag = headerCommitmentTag,
            manifestPolicyId = manifestPolicyId,
            manifestPolicyVersion = manifestPolicyVersion,
            storagePolicyId = storagePolicyId,
            storagePolicyVersion = storagePolicyVersion,
            encryptedRecordEntries = recordEntries,
            manifestSection = manifestSection,
            preUnlockIntegrityMetadata = preUnlockIntegrityMetadata,
        )
        validateContainer(container)?.let { return rejected(it) }
        return accepted(container)
    }

    internal fun hexToBytes(hex: String): ByteArray {
        require(hex.length % 2 == 0)
        return ByteArray(hex.length / 2) { index ->
            hex.substring(index * 2, index * 2 + 2).toInt(16).toByte()
        }
    }

    private fun recordsSectionBytes(records: List<SkaldVaultV1ContainerRecordEntry>): ByteArray =
        byteList {
            putU16(records.size)
            records.forEach { record ->
                putLengthPrefixedBytes(recordEntryBytes(record))
            }
        }

    private fun recordEntryBytes(record: SkaldVaultV1ContainerRecordEntry): ByteArray =
        byteList {
            putStringField(1, record.recordTypeId)
            putBytesField(2, record.recordId)
            putU64Field(3, record.recordVersionCounter)
            putBytesField(4, record.ciphertext)
            putBytesField(5, record.integrityCriticalRecordMetadata)
            putStringField(6, record.recordReference)
        }

    private fun manifestSectionBytes(section: SkaldVaultV1ContainerManifestSection): ByteArray =
        byteList {
            putStringField(1, section.manifestMagic)
            putStringField(2, section.manifestPolicyId)
            putU16Field(3, section.manifestPolicyVersion)
            putBytesField(4, section.vaultId)
            putStringField(5, section.providerSuiteId)
            putBytesField(6, section.headerCommitmentContext)
            putStringField(7, section.storageNamespace)
            putStringField(8, section.recordNamespace)
            putU64Field(9, section.manifestSequence)
            putBytesField(10, section.crashRecoveryMetadata)
            putBytesField(11, manifestRecordStatesBytes(section.recordStates))
        }

    private fun manifestRecordStatesBytes(
        states: List<SkaldVaultV1ContainerManifestRecordState>,
    ): ByteArray =
        byteList {
            putU16(states.size)
            states.forEach { state ->
                putLengthPrefixedBytes(manifestRecordStateBytes(state))
            }
        }

    private fun manifestRecordStateBytes(state: SkaldVaultV1ContainerManifestRecordState): ByteArray =
        byteList {
            putBytesField(1, state.recordId)
            putStringField(2, state.recordTypeId)
            putU64Field(3, state.latestRecordVersionCounter)
            putStringField(4, state.recordReference)
            putU16Field(5, if (state.tombstone) 1 else 0)
        }

    private fun parseRecordsSection(
        sectionResult: ReadResult<ByteArray>,
    ): ReadResult<List<SkaldVaultV1ContainerRecordEntry>> {
        return when (sectionResult) {
            is ReadResult.Err -> sectionResult
            is ReadResult.Ok -> {
                val reader = ByteReader(sectionResult.value)
                val count = when (val result = reader.readU16Value()) {
                    is ReadResult.Ok -> result.value
                    is ReadResult.Err -> return result
                }
                if (count > MAX_RECORDS) {
                    return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedLength)
                }
                val records = mutableListOf<SkaldVaultV1ContainerRecordEntry>()
                repeat(count) {
                    val entryBytes = when (val result = reader.readLengthPrefixedBytes()) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    }
                    records += when (val result = parseRecordEntry(entryBytes)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    }
                }
                if (reader.hasRemaining()) {
                    return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
                }
                ReadResult.Ok(records)
            }
        }
    }

    private fun parseRecordEntry(bytes: ByteArray): ReadResult<SkaldVaultV1ContainerRecordEntry> {
        val reader = ByteReader(bytes)
        val record = SkaldVaultV1ContainerRecordEntry(
            recordTypeId = when (val result = reader.readStringField(1)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            recordId = when (val result = reader.readBytesField(2)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            recordVersionCounter = when (val result = reader.readU64Field(3)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            ciphertext = when (val result = reader.readBytesField(4)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            integrityCriticalRecordMetadata = when (val result = reader.readBytesField(5)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            recordReference = when (val result = reader.readStringField(6)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
        )
        if (reader.hasRemaining()) {
            return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
        }
        validateRecord(record)?.let { return ReadResult.Err(it) }
        return ReadResult.Ok(record)
    }

    private fun parseManifestSection(
        sectionResult: ReadResult<ByteArray>,
    ): ReadResult<SkaldVaultV1ContainerManifestSection> {
        return when (sectionResult) {
            is ReadResult.Err -> sectionResult
            is ReadResult.Ok -> {
                val reader = ByteReader(sectionResult.value)
                val section = SkaldVaultV1ContainerManifestSection(
                    manifestMagic = when (val result = reader.readStringField(1)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    manifestPolicyId = when (val result = reader.readStringField(2)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    manifestPolicyVersion = when (val result = reader.readU16Field(3)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    vaultId = when (val result = reader.readBytesField(4)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    providerSuiteId = when (val result = reader.readStringField(5)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    headerCommitmentContext = when (val result = reader.readBytesField(6)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    storageNamespace = when (val result = reader.readStringField(7)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    recordNamespace = when (val result = reader.readStringField(8)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    manifestSequence = when (val result = reader.readU64Field(9)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    crashRecoveryMetadata = when (val result = reader.readBytesField(10)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                    recordStates = when (val result = parseManifestRecordStates(reader.readBytesField(11))) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    },
                )
                if (reader.hasRemaining()) {
                    return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
                }
                validateManifestSection(section)?.let { return ReadResult.Err(it) }
                ReadResult.Ok(section)
            }
        }
    }

    private fun parseManifestRecordStates(
        sectionResult: ReadResult<ByteArray>,
    ): ReadResult<List<SkaldVaultV1ContainerManifestRecordState>> {
        return when (sectionResult) {
            is ReadResult.Err -> sectionResult
            is ReadResult.Ok -> {
                val reader = ByteReader(sectionResult.value)
                val count = when (val result = reader.readU16Value()) {
                    is ReadResult.Ok -> result.value
                    is ReadResult.Err -> return result
                }
                if (count > MAX_RECORDS) {
                    return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedLength)
                }
                val states = mutableListOf<SkaldVaultV1ContainerManifestRecordState>()
                repeat(count) {
                    val stateBytes = when (val result = reader.readLengthPrefixedBytes()) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    }
                    states += when (val result = parseManifestRecordState(stateBytes)) {
                        is ReadResult.Ok -> result.value
                        is ReadResult.Err -> return result
                    }
                }
                if (reader.hasRemaining()) {
                    return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
                }
                ReadResult.Ok(states)
            }
        }
    }

    private fun parseManifestRecordState(
        bytes: ByteArray,
    ): ReadResult<SkaldVaultV1ContainerManifestRecordState> {
        val reader = ByteReader(bytes)
        val state = SkaldVaultV1ContainerManifestRecordState(
            recordId = when (val result = reader.readBytesField(1)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            recordTypeId = when (val result = reader.readStringField(2)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            latestRecordVersionCounter = when (val result = reader.readU64Field(3)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            recordReference = when (val result = reader.readStringField(4)) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            },
            tombstone = when (val result = reader.readU16Field(5)) {
                is ReadResult.Ok -> when (result.value) {
                    0 -> false
                    1 -> true
                    else -> return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue)
                }
                is ReadResult.Err -> return result
            },
        )
        if (reader.hasRemaining()) {
            return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TrailingBytes)
        }
        validateManifestRecordState(state)?.let { return ReadResult.Err(it) }
        return ReadResult.Ok(state)
    }

    private fun validateContainer(container: SkaldVaultV1Container): SkaldVaultV1ContainerRejectionReason? {
        if (container.vaultMagic != SkaldVaultV1HeaderCommitment.VAULT_MAGIC) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedMagic
        }
        if (container.vaultFormatVersion != SkaldVaultV1HeaderCommitment.VAULT_FORMAT_VERSION ||
            container.containerPolicyVersion != CONTAINER_POLICY_VERSION ||
            container.manifestPolicyVersion != MANIFEST_POLICY_VERSION ||
            container.storagePolicyVersion != STORAGE_POLICY_VERSION
        ) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedVersion
        }
        if (container.providerSuiteId != SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedSuiteId
        }
        if (container.kdfAlgorithmId != SkaldVaultV1HeaderCommitment.KDF_ALGORITHM_ID) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedKdfAlgorithm
        }
        if (container.kdfVersion != SkaldVaultV1HeaderCommitment.KDF_VERSION) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedKdfVersion
        }
        if (container.kdfMemoryKiB != SkaldVaultV1HeaderCommitment.KDF_MEMORY_KIB ||
            container.kdfTimeCost != SkaldVaultV1HeaderCommitment.KDF_TIME_COST ||
            container.kdfParallelism != SkaldVaultV1HeaderCommitment.KDF_PARALLELISM ||
            container.derivedRootMaterialBytes != SkaldVaultV1HeaderCommitment.ARGON2ID_ROOT_MATERIAL_BYTES
        ) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedKdfParameters
        }
        if (container.salt.size !in 16..64 ||
            container.vaultId.size != VAULT_ID_BYTES ||
            container.headerCommitmentTag.size != HEADER_COMMITMENT_TAG_BYTES ||
            container.preUnlockIntegrityMetadata.size > MAX_CONTAINER_METADATA_BYTES ||
            container.encryptedRecordEntries.size > MAX_RECORDS
        ) {
            return SkaldVaultV1ContainerRejectionReason.MalformedLength
        }
        listOf(
            container.vaultMagic,
            container.containerPolicyId,
            container.providerSuiteId,
            container.kdfAlgorithmId,
            container.passphrasePolicyId,
            container.keyExpansionPolicyId,
            container.keySeparationPolicyId,
            container.headerCommitmentPrimitivePolicyId,
            container.headerCommitmentPolicyId,
            container.recordFormatPolicyId,
            container.aadPolicyId,
            container.manifestPolicyId,
            container.storagePolicyId,
        ).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        if (container.containerPolicyId != CONTAINER_POLICY_ID ||
            container.passphrasePolicyId != SkaldVaultV1HeaderCommitment.PASSPHRASE_ENCODING_POLICY_ID ||
            container.keyExpansionPolicyId != SkaldVaultV1HeaderCommitment.KEY_EXPANSION_POLICY_ID ||
            container.keySeparationPolicyId != SkaldVaultV1HeaderCommitment.KEY_SEPARATION_POLICY_ID ||
            container.headerCommitmentPrimitivePolicyId !=
            SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_PRIMITIVE_POLICY_ID ||
            container.headerCommitmentPolicyId != SkaldVaultV1HeaderCommitment.HEADER_COMMITMENT_POLICY_ID ||
            container.recordFormatPolicyId != SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_ID ||
            container.aadPolicyId != SkaldVaultV1HeaderCommitment.AAD_POLICY_ID ||
            container.manifestPolicyId != MANIFEST_POLICY_ID ||
            container.storagePolicyId != STORAGE_POLICY_ID
        ) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId
        }
        if (container.recordFormatPolicyVersion != SkaldVaultV1HeaderCommitment.RECORD_FORMAT_POLICY_VERSION ||
            container.aadPolicyVersion != SkaldVaultV1HeaderCommitment.AAD_POLICY_VERSION
        ) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedVersion
        }
        if (container.featureFlags != SkaldVaultV1HeaderCommitment.FEATURE_FLAGS) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedVersion
        }
        container.encryptedRecordEntries.forEach { record ->
            validateRecord(record)?.let { return it }
        }
        validateManifestSection(container.manifestSection)?.let { return it }
        if (!container.manifestSection.vaultId.contentEquals(container.vaultId) ||
            container.manifestSection.providerSuiteId != container.providerSuiteId ||
            !container.manifestSection.headerCommitmentContext.contentEquals(container.headerCommitmentTag)
        ) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        val recordsById = container.encryptedRecordEntries.associateBy { it.recordId.toHex() }
        if (recordsById.size != container.encryptedRecordEntries.size ||
            container.manifestSection.recordStates.size != container.encryptedRecordEntries.size
        ) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        container.manifestSection.recordStates.forEach { state ->
            val record = recordsById[state.recordId.toHex()]
                ?: return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
            if (state.recordTypeId != record.recordTypeId ||
                state.latestRecordVersionCounter != record.recordVersionCounter ||
                state.recordReference != record.recordReference
            ) {
                return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
            }
        }
        return null
    }

    private fun validateManifestSection(
        section: SkaldVaultV1ContainerManifestSection,
    ): SkaldVaultV1ContainerRejectionReason? {
        if (section.manifestMagic != MANIFEST_MAGIC) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        if (section.manifestPolicyVersion != MANIFEST_POLICY_VERSION) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedVersion
        }
        if (section.manifestPolicyId != MANIFEST_POLICY_ID ||
            section.storageNamespace != SkaldVaultV1RecordAead.STORAGE_NAMESPACE ||
            section.recordNamespace != RECORD_NAMESPACE
        ) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId
        }
        if (section.vaultId.size != VAULT_ID_BYTES ||
            section.headerCommitmentContext.size != HEADER_COMMITMENT_TAG_BYTES ||
            section.crashRecoveryMetadata.size > MAX_CONTAINER_METADATA_BYTES ||
            section.recordStates.size > MAX_RECORDS
        ) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        if (section.providerSuiteId != SkaldVaultV1HeaderCommitment.PROVIDER_SUITE_ID) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedSuiteId
        }
        if (section.manifestSequence < 0) {
            return SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue
        }
        listOf(
            section.manifestMagic,
            section.manifestPolicyId,
            section.providerSuiteId,
            section.storageNamespace,
            section.recordNamespace,
        ).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        section.recordStates.forEach { state ->
            validateManifestRecordState(state)?.let { return it }
        }
        return null
    }

    private fun validateRecord(record: SkaldVaultV1ContainerRecordEntry): SkaldVaultV1ContainerRejectionReason? {
        if (SkaldVaultV1RecordType.entries.none { it.typeId == record.recordTypeId }) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedRecordType
        }
        if (record.recordId.size != RECORD_ID_BYTES ||
            record.ciphertext.isEmpty() ||
            record.ciphertext.size > MAX_RECORD_CIPHERTEXT_BYTES ||
            record.integrityCriticalRecordMetadata.size > MAX_RECORD_METADATA_BYTES ||
            record.recordReference.encodeToByteArray().size !in 1..MAX_RECORD_REFERENCE_BYTES
        ) {
            return SkaldVaultV1ContainerRejectionReason.MalformedRecordReference
        }
        if (record.recordVersionCounter < 0) {
            return SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue
        }
        listOf(record.recordTypeId, record.recordReference).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        return null
    }

    private fun validateManifestRecordState(
        state: SkaldVaultV1ContainerManifestRecordState,
    ): SkaldVaultV1ContainerRejectionReason? {
        if (state.recordId.size != RECORD_ID_BYTES) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        if (SkaldVaultV1RecordType.entries.none { it.typeId == state.recordTypeId }) {
            return SkaldVaultV1ContainerRejectionReason.UnsupportedRecordType
        }
        if (state.latestRecordVersionCounter < 0) {
            return SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue
        }
        if (state.recordReference.encodeToByteArray().size !in 1..MAX_RECORD_REFERENCE_BYTES) {
            return SkaldVaultV1ContainerRejectionReason.MalformedManifestSection
        }
        listOf(state.recordTypeId, state.recordReference).forEach { value ->
            validateAsciiString(value)?.let { return it }
        }
        return null
    }

    private fun validateAsciiString(value: String): SkaldVaultV1ContainerRejectionReason? {
        val bytes = value.encodeToByteArray()
        if (bytes.isEmpty() || bytes.size > UINT16_MAX) {
            return SkaldVaultV1ContainerRejectionReason.MalformedLength
        }
        if (bytes.any { byte -> byte.toInt() !in 0x20..0x7e }) {
            return SkaldVaultV1ContainerRejectionReason.MalformedStringValue
        }
        return null
    }

    private fun mapHeaderRejection(
        reason: SkaldVaultV1HeaderCommitmentRejectionReason,
    ): SkaldVaultV1ContainerRejectionReason =
        when (reason) {
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedSuiteId ->
                SkaldVaultV1ContainerRejectionReason.UnsupportedSuiteId
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfAlgorithm ->
                SkaldVaultV1ContainerRejectionReason.UnsupportedKdfAlgorithm
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfVersion ->
                SkaldVaultV1ContainerRejectionReason.UnsupportedKdfVersion
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKdfParameters ->
                SkaldVaultV1ContainerRejectionReason.UnsupportedKdfParameters
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedPolicyId,
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedKeyPurpose,
            -> SkaldVaultV1ContainerRejectionReason.UnsupportedPolicyId
            SkaldVaultV1HeaderCommitmentRejectionReason.UnsupportedVersion ->
                SkaldVaultV1ContainerRejectionReason.UnsupportedVersion
            SkaldVaultV1HeaderCommitmentRejectionReason.MalformedLength,
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidRootMaterialLength,
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentKeyLength,
            SkaldVaultV1HeaderCommitmentRejectionReason.InvalidHeaderCommitmentTagLength,
            -> SkaldVaultV1ContainerRejectionReason.MalformedLength
            SkaldVaultV1HeaderCommitmentRejectionReason.MalformedIntegerValue ->
                SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue
            SkaldVaultV1HeaderCommitmentRejectionReason.MalformedStringValue ->
                SkaldVaultV1ContainerRejectionReason.MalformedStringValue
        }

    private sealed class ReadResult<out T> {
        data class Ok<out T>(val value: T) : ReadResult<T>()
        data class Err(val reason: SkaldVaultV1ContainerRejectionReason) : ReadResult<Nothing>()
    }

    private class ByteReader(private val bytes: ByteArray) {
        private var position: Int = 0
        private var lastFieldId: Int = 0

        fun hasRemaining(): Boolean = position < bytes.size

        fun readStringField(expectedFieldId: Int): ReadResult<String> =
            when (val result = readBytesField(expectedFieldId)) {
                is ReadResult.Err -> result
                is ReadResult.Ok -> {
                    val value = result.value
                    if (value.isEmpty() || value.any { byte -> byte.toInt() !in 0x20..0x7e }) {
                        ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedStringValue)
                    } else {
                        ReadResult.Ok(value.decodeToString())
                    }
                }
            }

        fun readBytesField(expectedFieldId: Int): ReadResult<ByteArray> =
            when (val field = readFieldId(expectedFieldId)) {
                is ReadResult.Err -> field
                is ReadResult.Ok -> readLengthPrefixedBytes()
            }

        fun readU16Field(expectedFieldId: Int): ReadResult<Int> =
            when (val field = readFieldId(expectedFieldId)) {
                is ReadResult.Err -> field
                is ReadResult.Ok -> readU16Value()
            }

        fun readU32Field(expectedFieldId: Int): ReadResult<Int> =
            when (val field = readFieldId(expectedFieldId)) {
                is ReadResult.Err -> field
                is ReadResult.Ok -> readU32Value()
            }

        fun readU64Field(expectedFieldId: Int): ReadResult<Long> =
            when (val field = readFieldId(expectedFieldId)) {
                is ReadResult.Err -> field
                is ReadResult.Ok -> readU64Value()
            }

        fun readLengthPrefixedBytes(): ReadResult<ByteArray> {
            val length = when (val result = readU16Value()) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            }
            if (position + length > bytes.size) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TruncatedInput)
            }
            val value = bytes.copyOfRange(position, position + length)
            position += length
            return ReadResult.Ok(value)
        }

        fun readU16Value(): ReadResult<Int> {
            if (position + 2 > bytes.size) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TruncatedInput)
            }
            val value = ((bytes[position].toInt() and 0xff) shl 8) or
                (bytes[position + 1].toInt() and 0xff)
            position += 2
            return ReadResult.Ok(value)
        }

        private fun readU32Value(): ReadResult<Int> {
            if (position + 4 > bytes.size) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TruncatedInput)
            }
            var value = 0
            repeat(4) {
                value = (value shl 8) or (bytes[position + it].toInt() and 0xff)
            }
            position += 4
            if (value < 0) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue)
            }
            return ReadResult.Ok(value)
        }

        private fun readU64Value(): ReadResult<Long> {
            if (position + 8 > bytes.size) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.TruncatedInput)
            }
            var value = 0L
            repeat(8) {
                value = (value shl 8) or (bytes[position + it].toLong() and 0xffL)
            }
            position += 8
            if (value < 0L) {
                return ReadResult.Err(SkaldVaultV1ContainerRejectionReason.MalformedIntegerValue)
            }
            return ReadResult.Ok(value)
        }

        private fun readFieldId(expectedFieldId: Int): ReadResult<Unit> {
            val actualFieldId = when (val result = readU16Value()) {
                is ReadResult.Ok -> result.value
                is ReadResult.Err -> return result
            }
            if (actualFieldId != expectedFieldId) {
                return if (actualFieldId <= lastFieldId || actualFieldId < expectedFieldId) {
                    ReadResult.Err(SkaldVaultV1ContainerRejectionReason.DuplicatedField)
                } else {
                    ReadResult.Err(SkaldVaultV1ContainerRejectionReason.UnknownField)
                }
            }
            lastFieldId = actualFieldId
            return ReadResult.Ok(Unit)
        }
    }

    private fun byteList(block: MutableList<Byte>.() -> Unit): ByteArray =
        mutableListOf<Byte>().apply(block).toByteArray()

    private fun MutableList<Byte>.putStringField(fieldId: Int, value: String) {
        putU16(fieldId)
        putLengthPrefixedBytes(value.encodeToByteArray())
    }

    private fun MutableList<Byte>.putBytesField(fieldId: Int, value: ByteArray) {
        putU16(fieldId)
        putLengthPrefixedBytes(value)
    }

    private fun MutableList<Byte>.putU16Field(fieldId: Int, value: Int) {
        putU16(fieldId)
        putU16(value)
    }

    private fun MutableList<Byte>.putU32Field(fieldId: Int, value: Int) {
        putU16(fieldId)
        putU32(value)
    }

    private fun MutableList<Byte>.putU64Field(fieldId: Int, value: Long) {
        putU16(fieldId)
        putU64(value)
    }

    private fun MutableList<Byte>.putLengthPrefixedBytes(value: ByteArray) {
        putU16(value.size)
        value.forEach { add(it) }
    }

    private fun MutableList<Byte>.putU16(value: Int) {
        require(value in 0..UINT16_MAX)
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun MutableList<Byte>.putU32(value: Int) {
        require(value >= 0)
        add(((value ushr 24) and 0xff).toByte())
        add(((value ushr 16) and 0xff).toByte())
        add(((value ushr 8) and 0xff).toByte())
        add((value and 0xff).toByte())
    }

    private fun MutableList<Byte>.putU64(value: Long) {
        require(value >= 0)
        for (shift in 56 downTo 0 step 8) {
            add(((value ushr shift) and 0xff).toByte())
        }
    }

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> byte.toUByte().toString(16).padStart(2, '0') }

    private fun <T> accepted(value: T): SkaldVaultV1ContainerResult.Accepted<T> =
        SkaldVaultV1ContainerResult.Accepted(value)

    private fun rejected(reason: SkaldVaultV1ContainerRejectionReason): SkaldVaultV1ContainerResult.Rejected =
        SkaldVaultV1ContainerResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 container input rejected: ${reason.label}.",
        )
}
