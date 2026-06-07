package com.libertasprimordium.skald.security

enum class AndroidVaultDeviceClassTestingPolicy(
    val label: String,
    val requiredForCompatibilityPlanning: Boolean,
) {
    LowEndModelTestingForegone(
        label = "low-end Android model testing foregone",
        requiredForCompatibilityPlanning = false,
    ),
    MidRangeModelTestingForegone(
        label = "mid-range Android model testing foregone",
        requiredForCompatibilityPlanning = false,
    ),
    HighEndEvidenceRetained(
        label = "high-end Android evidence retained",
        requiredForCompatibilityPlanning = false,
    ),
}

data class AndroidMinimumCompatibilityPolicy(
    val minimumApiLevel: Int,
    val veryOldAndroidCompatibilityGoal: Boolean,
    val lowEndModelTestingRequired: Boolean,
    val midRangeModelTestingRequired: Boolean,
    val runtimeCryptoProviderCheckRequired: Boolean,
    val runtimePrimitiveCheckRequired: Boolean,
    val runtimeRandomnessCheckRequired: Boolean,
) {
    fun supports(apiLevel: Int): Boolean = apiLevel >= minimumApiLevel
}

enum class VaultRandomnessSourceKind(
    val label: String,
    val approvedForVaultMaterial: Boolean,
    val languageOrAdHocApi: Boolean,
    val keyProtectionOnly: Boolean,
) {
    OsCryptographicRandomness(
        label = "OS cryptographic randomness",
        approvedForVaultMaterial = true,
        languageOrAdHocApi = false,
        keyProtectionOnly = false,
    ),
    ReviewedCryptoProviderRandomness(
        label = "reviewed crypto-provider randomness",
        approvedForVaultMaterial = true,
        languageOrAdHocApi = false,
        keyProtectionOnly = false,
    ),
    HardwareBackedKeyProtectionOnly(
        label = "hardware-backed key protection only",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = false,
        keyProtectionOnly = true,
    ),
    KotlinRandom(
        label = "Kotlin general-purpose random API",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    JavaUtilRandom(
        label = "Java general-purpose random API",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    MathRandom(
        label = "math random API",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    TimestampDerived(
        label = "timestamp-derived pseudo-random value",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    UuidDerived(
        label = "identifier-derived pseudo-random value",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    AdHocPrng(
        label = "ad hoc PRNG",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = true,
        keyProtectionOnly = false,
    ),
    Unknown(
        label = "unknown randomness source",
        approvedForVaultMaterial = false,
        languageOrAdHocApi = false,
        keyProtectionOnly = false,
    ),
}

enum class VaultRandomnessUse(val label: String) {
    VaultRootKey("vault root key"),
    RecordKey("record key"),
    Salt("KDF salt"),
    Nonce("record nonce"),
    BackupKey("backup key"),
    UnlockMaterial("unlock material"),
}

enum class PlatformKeyProtectionAvailability(
    val label: String,
    val satisfiesBasicVaultCompatibility: Boolean,
) {
    HardwareBackedAvailable(
        label = "hardware-backed key protection available",
        satisfiesBasicVaultCompatibility = true,
    ),
    SoftwareOrOsOnly(
        label = "software or OS-only key protection",
        satisfiesBasicVaultCompatibility = true,
    ),
    Unavailable(
        label = "hardware-backed key protection unavailable",
        satisfiesBasicVaultCompatibility = true,
    ),
    Unknown(
        label = "key protection availability unknown",
        satisfiesBasicVaultCompatibility = false,
    ),
}

data class EntropySourcePolicy(
    val osCryptographicRandomnessAllowed: Boolean,
    val reviewedCryptoProviderRandomnessAllowed: Boolean,
    val softwareOsCsprngFallbackAllowed: Boolean,
    val hardwareBackedKeyProtectionPreferred: Boolean,
    val hardwareBackedKeyProtectionRequired: Boolean,
    val strongBoxOptionalPreferredIfAvailable: Boolean,
    val forbiddenRandomnessSources: Set<VaultRandomnessSourceKind>,
    val protectedUses: Set<VaultRandomnessUse>,
) {
    fun accepts(source: VaultRandomnessSourceKind): Boolean =
        source.approvedForVaultMaterial &&
            when (source) {
                VaultRandomnessSourceKind.OsCryptographicRandomness -> osCryptographicRandomnessAllowed
                VaultRandomnessSourceKind.ReviewedCryptoProviderRandomness -> reviewedCryptoProviderRandomnessAllowed
                else -> false
            }
}

enum class AndroidVaultRuntimeCheckState(
    val label: String,
    val satisfied: Boolean,
    val known: Boolean,
) {
    Passed("passed", satisfied = true, known = true),
    Missing("missing", satisfied = false, known = true),
    Failed("failed", satisfied = false, known = true),
    Unknown("unknown", satisfied = false, known = false),
}

enum class VaultCreationFailClosedReason(val label: String) {
    UnsupportedOutdatedAndroid("unsupported or outdated Android version"),
    RuntimeCryptoProviderCheckMissing("runtime crypto provider check missing"),
    RuntimePrimitiveCheckMissing("runtime primitive check missing"),
    RuntimeRandomnessCheckMissing("runtime cryptographic randomness check missing"),
    UnknownProviderState("provider state unknown"),
    UnknownEntropyState("entropy or randomness source state unknown"),
    ForbiddenRandomnessSource("forbidden non-cryptographic randomness source"),
    HardwareKeyProtectionMistakenForRandomness("hardware-backed key protection was treated as randomness"),
    ProductionProviderMissing("production provider implementation missing"),
    ProviderSelectionDisabled("provider selection remains disabled-only"),
    ProductionKdfDisabled("production KDF execution disabled"),
    SecureSecretStorageDisabled("secure secret storage disabled"),
    SecureMetadataStorageDisabled("secure metadata storage disabled"),
    VaultContainerStorageReviewMissing("vault container/storage review missing"),
}

data class UserFacingVaultCreationWarning(
    val reason: VaultCreationFailClosedReason,
    val title: String,
    val safeMessage: String,
)

data class AndroidVaultCompatibilityEvidence(
    val apiLevel: Int,
    val cryptoProviderRuntimeCheck: AndroidVaultRuntimeCheckState,
    val primitiveRuntimeCheck: AndroidVaultRuntimeCheckState,
    val randomnessRuntimeCheck: AndroidVaultRuntimeCheckState,
    val randomnessSource: VaultRandomnessSourceKind,
    val hardwareBackedKeyProtection: PlatformKeyProtectionAvailability,
    val strongBox: PlatformKeyProtectionAvailability,
) {
    companion object {
        fun supportedPlanningEvidence(
            apiLevel: Int = commonAndroidVaultCompatibilityPolicy().minimumCompatibility.minimumApiLevel,
            randomnessSource: VaultRandomnessSourceKind = VaultRandomnessSourceKind.OsCryptographicRandomness,
            hardwareBackedKeyProtection: PlatformKeyProtectionAvailability =
                PlatformKeyProtectionAvailability.Unavailable,
            strongBox: PlatformKeyProtectionAvailability = PlatformKeyProtectionAvailability.Unavailable,
        ): AndroidVaultCompatibilityEvidence =
            AndroidVaultCompatibilityEvidence(
                apiLevel = apiLevel,
                cryptoProviderRuntimeCheck = AndroidVaultRuntimeCheckState.Passed,
                primitiveRuntimeCheck = AndroidVaultRuntimeCheckState.Passed,
                randomnessRuntimeCheck = AndroidVaultRuntimeCheckState.Passed,
                randomnessSource = randomnessSource,
                hardwareBackedKeyProtection = hardwareBackedKeyProtection,
                strongBox = strongBox,
            )
    }
}

data class AndroidVaultCompatibilityAssessmentRequest(
    val evidence: AndroidVaultCompatibilityEvidence,
    val productionProviderImplementationExists: Boolean = false,
    val providerSelectionDisabledOnly: Boolean = true,
    val productionKdfEnabled: Boolean = false,
    val secureSecretStorageAvailable: Boolean = false,
    val secureMetadataStorageAvailable: Boolean = false,
    val vaultContainerStorageReviewComplete: Boolean = false,
)

data class AndroidVaultCompatibilityAssessment(
    val policy: AndroidSupportedPlatformPolicy,
    val evidence: AndroidVaultCompatibilityEvidence,
    val compatibilityPlanningSatisfied: Boolean,
    val vaultCreationAllowed: Boolean,
    val productionProviderApproved: Boolean,
    val productionKdfApproved: Boolean,
    val lowEndModelTestingRequired: Boolean,
    val midRangeModelTestingRequired: Boolean,
    val exhaustiveDevicePerformanceProven: Boolean,
    val randomnessAcceptedForVaultMaterial: Boolean,
    val hardwareBackedKeyProtectionRequired: Boolean,
    val strongBoxRequired: Boolean,
    val failClosedReasons: Set<VaultCreationFailClosedReason>,
    val userFacingWarnings: List<UserFacingVaultCreationWarning>,
) {
    val requiresUserFacingWarning: Boolean
        get() = failClosedReasons.isNotEmpty()
}

data class AndroidSupportedPlatformPolicy(
    val minimumCompatibility: AndroidMinimumCompatibilityPolicy,
    val deviceClassTesting: Set<AndroidVaultDeviceClassTestingPolicy>,
    val entropySourcePolicy: EntropySourcePolicy,
    val failClosedByDefault: Boolean,
) {
    fun assess(request: AndroidVaultCompatibilityAssessmentRequest): AndroidVaultCompatibilityAssessment {
        val reasons = buildSet {
            if (!minimumCompatibility.supports(request.evidence.apiLevel)) {
                add(VaultCreationFailClosedReason.UnsupportedOutdatedAndroid)
            }
            if (!request.evidence.cryptoProviderRuntimeCheck.known) {
                add(VaultCreationFailClosedReason.UnknownProviderState)
            } else if (!request.evidence.cryptoProviderRuntimeCheck.satisfied) {
                add(VaultCreationFailClosedReason.RuntimeCryptoProviderCheckMissing)
            }
            if (!request.evidence.primitiveRuntimeCheck.satisfied) {
                add(VaultCreationFailClosedReason.RuntimePrimitiveCheckMissing)
            }
            if (!request.evidence.randomnessRuntimeCheck.known) {
                add(VaultCreationFailClosedReason.UnknownEntropyState)
            } else if (!request.evidence.randomnessRuntimeCheck.satisfied) {
                add(VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing)
            }
            if (request.evidence.randomnessSource == VaultRandomnessSourceKind.Unknown) {
                add(VaultCreationFailClosedReason.UnknownEntropyState)
            }
            if (request.evidence.randomnessSource.languageOrAdHocApi) {
                add(VaultCreationFailClosedReason.ForbiddenRandomnessSource)
            }
            if (request.evidence.randomnessSource.keyProtectionOnly) {
                add(VaultCreationFailClosedReason.HardwareKeyProtectionMistakenForRandomness)
            }
            if (!entropySourcePolicy.accepts(request.evidence.randomnessSource)) {
                add(VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing)
            }
            if (!request.productionProviderImplementationExists) {
                add(VaultCreationFailClosedReason.ProductionProviderMissing)
            }
            if (request.providerSelectionDisabledOnly) {
                add(VaultCreationFailClosedReason.ProviderSelectionDisabled)
            }
            if (!request.productionKdfEnabled) {
                add(VaultCreationFailClosedReason.ProductionKdfDisabled)
            }
            if (!request.secureSecretStorageAvailable) {
                add(VaultCreationFailClosedReason.SecureSecretStorageDisabled)
            }
            if (!request.secureMetadataStorageAvailable) {
                add(VaultCreationFailClosedReason.SecureMetadataStorageDisabled)
            }
            if (!request.vaultContainerStorageReviewComplete) {
                add(VaultCreationFailClosedReason.VaultContainerStorageReviewMissing)
            }
        }
        val compatibilityReasons = reasons.intersect(compatibilityPlanningReasons)
        return AndroidVaultCompatibilityAssessment(
            policy = this,
            evidence = request.evidence,
            compatibilityPlanningSatisfied = compatibilityReasons.isEmpty(),
            vaultCreationAllowed = reasons.isEmpty(),
            productionProviderApproved = false,
            productionKdfApproved = false,
            lowEndModelTestingRequired = minimumCompatibility.lowEndModelTestingRequired,
            midRangeModelTestingRequired = minimumCompatibility.midRangeModelTestingRequired,
            exhaustiveDevicePerformanceProven = false,
            randomnessAcceptedForVaultMaterial = entropySourcePolicy.accepts(request.evidence.randomnessSource),
            hardwareBackedKeyProtectionRequired = entropySourcePolicy.hardwareBackedKeyProtectionRequired,
            strongBoxRequired = false,
            failClosedReasons = reasons,
            userFacingWarnings = reasons.map { it.warning() },
        )
    }

    private fun VaultCreationFailClosedReason.warning(): UserFacingVaultCreationWarning =
        UserFacingVaultCreationWarning(
            reason = this,
            title = "Vault creation blocked",
            safeMessage = "Vault creation is unavailable because ${label}.",
        )

    companion object {
        private val compatibilityPlanningReasons = setOf(
            VaultCreationFailClosedReason.UnsupportedOutdatedAndroid,
            VaultCreationFailClosedReason.RuntimeCryptoProviderCheckMissing,
            VaultCreationFailClosedReason.RuntimePrimitiveCheckMissing,
            VaultCreationFailClosedReason.RuntimeRandomnessCheckMissing,
            VaultCreationFailClosedReason.UnknownProviderState,
            VaultCreationFailClosedReason.UnknownEntropyState,
            VaultCreationFailClosedReason.ForbiddenRandomnessSource,
            VaultCreationFailClosedReason.HardwareKeyProtectionMistakenForRandomness,
        )

        fun current(): AndroidSupportedPlatformPolicy =
            AndroidSupportedPlatformPolicy(
                minimumCompatibility = AndroidMinimumCompatibilityPolicy(
                    minimumApiLevel = 26,
                    veryOldAndroidCompatibilityGoal = false,
                    lowEndModelTestingRequired = false,
                    midRangeModelTestingRequired = false,
                    runtimeCryptoProviderCheckRequired = true,
                    runtimePrimitiveCheckRequired = true,
                    runtimeRandomnessCheckRequired = true,
                ),
                deviceClassTesting = AndroidVaultDeviceClassTestingPolicy.entries.toSet(),
                entropySourcePolicy = EntropySourcePolicy(
                    osCryptographicRandomnessAllowed = true,
                    reviewedCryptoProviderRandomnessAllowed = true,
                    softwareOsCsprngFallbackAllowed = true,
                    hardwareBackedKeyProtectionPreferred = true,
                    hardwareBackedKeyProtectionRequired = false,
                    strongBoxOptionalPreferredIfAvailable = true,
                    forbiddenRandomnessSources = setOf(
                        VaultRandomnessSourceKind.KotlinRandom,
                        VaultRandomnessSourceKind.JavaUtilRandom,
                        VaultRandomnessSourceKind.MathRandom,
                        VaultRandomnessSourceKind.TimestampDerived,
                        VaultRandomnessSourceKind.UuidDerived,
                        VaultRandomnessSourceKind.AdHocPrng,
                    ),
                    protectedUses = VaultRandomnessUse.entries.toSet(),
                ),
                failClosedByDefault = true,
            )
    }
}

fun commonAndroidVaultCompatibilityPolicy(): AndroidSupportedPlatformPolicy =
    AndroidSupportedPlatformPolicy.current()

fun commonAndroidVaultCompatibilityUnknownAssessment(): AndroidVaultCompatibilityAssessment =
    commonAndroidVaultCompatibilityPolicy().assess(
        AndroidVaultCompatibilityAssessmentRequest(
            evidence = AndroidVaultCompatibilityEvidence(
                apiLevel = commonAndroidVaultCompatibilityPolicy().minimumCompatibility.minimumApiLevel,
                cryptoProviderRuntimeCheck = AndroidVaultRuntimeCheckState.Unknown,
                primitiveRuntimeCheck = AndroidVaultRuntimeCheckState.Unknown,
                randomnessRuntimeCheck = AndroidVaultRuntimeCheckState.Unknown,
                randomnessSource = VaultRandomnessSourceKind.Unknown,
                hardwareBackedKeyProtection = PlatformKeyProtectionAvailability.Unknown,
                strongBox = PlatformKeyProtectionAvailability.Unknown,
            ),
        ),
    )
