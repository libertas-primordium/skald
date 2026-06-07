package com.libertasprimordium.skald.security

enum class RuntimeRandomnessSourceKind(
    val label: String,
    val acceptedForVaultMaterial: Boolean,
    val keyProtectionOnly: Boolean,
    val forbiddenLanguageOrAdHocSource: Boolean,
) {
    OsCryptographicRandomness(
        label = "OS cryptographic randomness",
        acceptedForVaultMaterial = true,
        keyProtectionOnly = false,
        forbiddenLanguageOrAdHocSource = false,
    ),
    ReviewedCryptoProviderRandomness(
        label = "reviewed crypto-provider randomness",
        acceptedForVaultMaterial = true,
        keyProtectionOnly = false,
        forbiddenLanguageOrAdHocSource = false,
    ),
    HardwareBackedKeyProtectionOnly(
        label = "hardware-backed key protection only",
        acceptedForVaultMaterial = false,
        keyProtectionOnly = true,
        forbiddenLanguageOrAdHocSource = false,
    ),
    ForbiddenLanguageRandom(
        label = "forbidden language-level random API",
        acceptedForVaultMaterial = false,
        keyProtectionOnly = false,
        forbiddenLanguageOrAdHocSource = true,
    ),
    Unknown(
        label = "unknown randomness source",
        acceptedForVaultMaterial = false,
        keyProtectionOnly = false,
        forbiddenLanguageOrAdHocSource = false,
    ),
}

enum class ForbiddenRandomApi(
    val label: String,
    val sourceKind: RuntimeRandomnessSourceKind,
) {
    KotlinRandom("Kotlin general-purpose random API", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
    JavaUtilRandom("Java general-purpose random API", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
    MathRandom("math-library random API", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
    TimestampDerived("timestamp-derived pseudo-random value", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
    UuidDerived("identifier-derived pseudo-random value", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
    AdHocPrng("ad hoc pseudo-random generator", RuntimeRandomnessSourceKind.ForbiddenLanguageRandom),
}

enum class RuntimeRandomnessCheckState(
    val label: String,
    val available: Boolean,
    val known: Boolean,
) {
    Passed("passed", available = true, known = true),
    Failed("failed", available = false, known = true),
    Missing("missing", available = false, known = true),
    Unknown("unknown", available = false, known = false),
}

enum class RuntimeRandomnessBlocker(val label: String) {
    ApprovedRandomnessUnavailable("approved cryptographic randomness unavailable"),
    UnknownRandomnessSource("randomness source unknown"),
    UnknownProviderState("runtime provider state unknown"),
    ForbiddenLanguageRandomApi("forbidden language-level or ad hoc random API"),
    HardwareKeyProtectionMistakenForRandomness("hardware-backed key protection mistaken for randomness"),
    NonSecretSamplePersisted("non-secret availability sample persisted"),
    NonSecretSampleLogged("non-secret availability sample logged"),
    NonSecretSampleUsedAsVaultMaterial("non-secret availability sample used as vault material"),
    NonSecretSampleMisrepresentedAsEntropyQualityProof("non-secret availability sample misrepresented as entropy-quality proof"),
    ProductionEntropyCollectionAttempted("production entropy collection attempted"),
}

data class RuntimeRandomnessSampleEvidence(
    val nonSecretSampleGenerated: Boolean,
    val sampleByteCount: Int,
    val samplePersisted: Boolean,
    val sampleLogged: Boolean,
    val sampleUsedAsVaultMaterial: Boolean,
    val entropyQualityProven: Boolean,
) {
    companion object {
        fun noSample(): RuntimeRandomnessSampleEvidence =
            RuntimeRandomnessSampleEvidence(
                nonSecretSampleGenerated = false,
                sampleByteCount = 0,
                samplePersisted = false,
                sampleLogged = false,
                sampleUsedAsVaultMaterial = false,
                entropyQualityProven = false,
            )

        fun nonSecretAvailabilitySample(sampleByteCount: Int): RuntimeRandomnessSampleEvidence =
            RuntimeRandomnessSampleEvidence(
                nonSecretSampleGenerated = true,
                sampleByteCount = sampleByteCount,
                samplePersisted = false,
                sampleLogged = false,
                sampleUsedAsVaultMaterial = false,
                entropyQualityProven = false,
            )
    }
}

data class RuntimeRandomnessAvailabilityCheck(
    val platform: EncryptedVaultPlatform,
    val sourceKind: RuntimeRandomnessSourceKind,
    val state: RuntimeRandomnessCheckState,
    val providerName: String?,
    val algorithmName: String?,
    val sampleEvidence: RuntimeRandomnessSampleEvidence = RuntimeRandomnessSampleEvidence.noSample(),
    val productionEntropyCollection: Boolean = false,
    val safeFailureReason: String? = null,
)

data class UserFacingRandomnessFailureWarning(
    val title: String,
    val safeMessage: String,
    val blockers: Set<RuntimeRandomnessBlocker>,
)

data class RuntimeRandomnessCheckResult(
    val check: RuntimeRandomnessAvailabilityCheck,
    val acceptedForCompatibilityPlanning: Boolean,
    val acceptedForVaultMaterial: Boolean,
    val entropyQualityProven: Boolean,
    val productionEntropyCollection: Boolean,
    val blockers: Set<RuntimeRandomnessBlocker>,
    val userFacingWarning: UserFacingRandomnessFailureWarning?,
    val safeDetail: String,
) {
    val vaultCreationRandomnessGateSatisfied: Boolean
        get() = acceptedForCompatibilityPlanning && blockers.isEmpty()
}

data class RuntimeProviderPrimitiveCheckResult(
    val platform: EncryptedVaultPlatform,
    val providerRuntimeCheck: RuntimeRandomnessCheckState,
    val primitiveRuntimeCheck: RuntimeRandomnessCheckState,
    val randomnessCheck: RuntimeRandomnessCheckResult,
    val productionProviderApproved: Boolean = false,
    val safeDetail: String,
) {
    val compatibilityPlanningSatisfied: Boolean
        get() = providerRuntimeCheck.available &&
            primitiveRuntimeCheck.available &&
            randomnessCheck.vaultCreationRandomnessGateSatisfied

    val productionProviderSelectable: Boolean = false
}

data class VaultCreationRandomnessGate(
    val providerPrimitiveCheck: RuntimeProviderPrimitiveCheckResult,
    val failClosedReasons: Set<VaultCreationFailClosedReason>,
    val userFacingWarnings: List<UserFacingRandomnessFailureWarning>,
) {
    val randomnessGateSatisfied: Boolean
        get() = failClosedReasons.none {
            it == VaultCreationFailClosedReason.RuntimeCryptoProviderCheckMissing ||
                it == VaultCreationFailClosedReason.RuntimePrimitiveCheckMissing ||
                it == VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing ||
                it == VaultCreationFailClosedReason.UnknownProviderState ||
                it == VaultCreationFailClosedReason.UnknownEntropyState ||
                it == VaultCreationFailClosedReason.ForbiddenRandomnessSource ||
                it == VaultCreationFailClosedReason.HardwareKeyProtectionMistakenForRandomness
        }

    val vaultCreationAllowed: Boolean = false
}

data class RuntimeRandomnessProviderPolicy(
    val acceptedSources: Set<RuntimeRandomnessSourceKind>,
    val forbiddenApis: Set<ForbiddenRandomApi>,
    val protectedUses: Set<VaultRandomnessUse>,
    val osCryptographicRandomnessAllowed: Boolean,
    val reviewedCryptoProviderRandomnessAllowed: Boolean,
    val hardwareBackedKeyProtectionPreferred: Boolean,
    val hardwareBackedKeyProtectionRequired: Boolean,
) {
    fun evaluate(check: RuntimeRandomnessAvailabilityCheck): RuntimeRandomnessCheckResult {
        val blockers = buildSet {
            if (!check.state.known) {
                add(RuntimeRandomnessBlocker.UnknownProviderState)
            } else if (!check.state.available) {
                add(RuntimeRandomnessBlocker.ApprovedRandomnessUnavailable)
            }
            if (check.sourceKind == RuntimeRandomnessSourceKind.Unknown) {
                add(RuntimeRandomnessBlocker.UnknownRandomnessSource)
            }
            if (check.sourceKind.forbiddenLanguageOrAdHocSource) {
                add(RuntimeRandomnessBlocker.ForbiddenLanguageRandomApi)
            }
            if (check.sourceKind.keyProtectionOnly) {
                add(RuntimeRandomnessBlocker.HardwareKeyProtectionMistakenForRandomness)
            }
            if (check.sourceKind !in acceptedSources) {
                add(RuntimeRandomnessBlocker.ApprovedRandomnessUnavailable)
            }
            if (check.sampleEvidence.samplePersisted) {
                add(RuntimeRandomnessBlocker.NonSecretSamplePersisted)
            }
            if (check.sampleEvidence.sampleLogged) {
                add(RuntimeRandomnessBlocker.NonSecretSampleLogged)
            }
            if (check.sampleEvidence.sampleUsedAsVaultMaterial) {
                add(RuntimeRandomnessBlocker.NonSecretSampleUsedAsVaultMaterial)
            }
            if (check.sampleEvidence.entropyQualityProven) {
                add(RuntimeRandomnessBlocker.NonSecretSampleMisrepresentedAsEntropyQualityProof)
            }
            if (check.productionEntropyCollection) {
                add(RuntimeRandomnessBlocker.ProductionEntropyCollectionAttempted)
            }
        }
        val acceptedForVaultMaterial = check.state.available &&
            check.sourceKind.acceptedForVaultMaterial &&
            check.sourceKind in acceptedSources &&
            blockers.isEmpty()
        return RuntimeRandomnessCheckResult(
            check = check,
            acceptedForCompatibilityPlanning = acceptedForVaultMaterial,
            acceptedForVaultMaterial = acceptedForVaultMaterial,
            entropyQualityProven = false,
            productionEntropyCollection = check.productionEntropyCollection,
            blockers = blockers,
            userFacingWarning = if (blockers.isEmpty()) null else warning(blockers),
            safeDetail = if (blockers.isEmpty()) {
                "Runtime randomness availability check accepted for compatibility planning only; it is not entropy-quality proof and does not implement production entropy collection."
            } else {
                "Runtime randomness availability check failed closed."
            },
        )
    }

    fun gate(providerPrimitiveCheck: RuntimeProviderPrimitiveCheckResult): VaultCreationRandomnessGate {
        val reasons = buildSet {
            if (!providerPrimitiveCheck.providerRuntimeCheck.known) {
                add(VaultCreationFailClosedReason.UnknownProviderState)
            } else if (!providerPrimitiveCheck.providerRuntimeCheck.available) {
                add(VaultCreationFailClosedReason.RuntimeCryptoProviderCheckMissing)
            }
            if (!providerPrimitiveCheck.primitiveRuntimeCheck.available) {
                add(VaultCreationFailClosedReason.RuntimePrimitiveCheckMissing)
            }
            if (!providerPrimitiveCheck.randomnessCheck.check.state.known ||
                RuntimeRandomnessBlocker.UnknownRandomnessSource in providerPrimitiveCheck.randomnessCheck.blockers
            ) {
                add(VaultCreationFailClosedReason.UnknownEntropyState)
            }
            if (!providerPrimitiveCheck.randomnessCheck.acceptedForCompatibilityPlanning) {
                add(VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing)
            }
            if (RuntimeRandomnessBlocker.ForbiddenLanguageRandomApi in providerPrimitiveCheck.randomnessCheck.blockers) {
                add(VaultCreationFailClosedReason.ForbiddenRandomnessSource)
            }
            if (
                RuntimeRandomnessBlocker.HardwareKeyProtectionMistakenForRandomness in
                providerPrimitiveCheck.randomnessCheck.blockers
            ) {
                add(VaultCreationFailClosedReason.HardwareKeyProtectionMistakenForRandomness)
            }
        }
        return VaultCreationRandomnessGate(
            providerPrimitiveCheck = providerPrimitiveCheck,
            failClosedReasons = reasons,
            userFacingWarnings = providerPrimitiveCheck.randomnessCheck.userFacingWarning?.let(::listOf)
                ?: emptyList(),
        )
    }

    private fun warning(blockers: Set<RuntimeRandomnessBlocker>): UserFacingRandomnessFailureWarning =
        UserFacingRandomnessFailureWarning(
            title = "Vault creation blocked",
            safeMessage = "Approved cryptographic randomness is unavailable or unverified; vault creation must fail closed.",
            blockers = blockers,
        )

    companion object {
        fun current(): RuntimeRandomnessProviderPolicy =
            RuntimeRandomnessProviderPolicy(
                acceptedSources = setOf(
                    RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                    RuntimeRandomnessSourceKind.ReviewedCryptoProviderRandomness,
                ),
                forbiddenApis = ForbiddenRandomApi.entries.toSet(),
                protectedUses = VaultRandomnessUse.entries.toSet(),
                osCryptographicRandomnessAllowed = true,
                reviewedCryptoProviderRandomnessAllowed = true,
                hardwareBackedKeyProtectionPreferred = true,
                hardwareBackedKeyProtectionRequired = false,
            )
    }
}

fun commonRuntimeRandomnessProviderPolicy(): RuntimeRandomnessProviderPolicy =
    RuntimeRandomnessProviderPolicy.current()
