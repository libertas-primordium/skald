package com.libertasprimordium.skald.security

enum class Argon2idCalibrationImplementationStatus(
    val label: String,
    val calibrationComplete: Boolean,
    val productionKdfEnabled: Boolean,
) {
    PolicyPresentProbeOnly(
        label = "calibration policy present; probe only",
        calibrationComplete = false,
        productionKdfEnabled = false,
    ),
}

enum class Argon2idVersion(
    val label: String,
    val numericVersion: Int,
) {
    Version19(label = "Argon2 version 19", numericVersion = 19),
}

enum class Argon2idMemoryUnit(val label: String, val kibMultiplier: Int) {
    KiB(label = "KiB", kibMultiplier = 1),
    MiB(label = "MiB", kibMultiplier = 1024),
}

enum class Argon2idCalibrationPlatformClass(val label: String) {
    LinuxDesktopJvm("Linux desktop JVM"),
    AndroidRuntime("Android runtime"),
    AndroidMemoryConstrained("Android memory-constrained device class"),
    DesktopExtendedProbe("desktop extended probe"),
}

enum class Argon2idTargetLatencyBand(
    val label: String,
    val lowerBoundMillis: Long,
    val upperBoundMillis: Long,
    val productionTarget: Boolean,
) {
    SmokeProbeOnly("smoke probe only", lowerBoundMillis = 0, upperBoundMillis = 250, productionTarget = false),
    InteractiveUnlockCandidate("interactive unlock candidate", lowerBoundMillis = 250, upperBoundMillis = 1500, productionTarget = false),
    ExtendedDesktopProbe("extended desktop probe", lowerBoundMillis = 1500, upperBoundMillis = 4000, productionTarget = false),
}

enum class Argon2idParameterPolicyStatus(
    val label: String,
    val finalProductionApproved: Boolean,
    val productionKdfEnabled: Boolean,
) {
    CandidatePolicyPresentNotFinal(
        label = "candidate parameter policy present; not final",
        finalProductionApproved = false,
        productionKdfEnabled = false,
    ),
}

enum class Argon2idParameterTierKind(
    val label: String,
    val finalProductionApproved: Boolean,
    val universalAndroidPolicy: Boolean,
) {
    DesktopCandidate(
        label = "desktop candidate tier",
        finalProductionApproved = false,
        universalAndroidPolicy = false,
    ),
    HighEndAndroidCandidate(
        label = "high-end Android candidate tier",
        finalProductionApproved = false,
        universalAndroidPolicy = false,
    ),
    MobileFallbackProbeFloor(
        label = "mobile fallback/probe floor tier",
        finalProductionApproved = false,
        universalAndroidPolicy = false,
    ),
    AndroidBaselineUnresolved(
        label = "Android baseline unresolved tier",
        finalProductionApproved = false,
        universalAndroidPolicy = false,
    ),
}

enum class Argon2idDeviceClassEvidenceStatus(
    val label: String,
    val satisfiesAndroidBaselineCoverage: Boolean,
) {
    DesktopJvmProbeMeasured("Linux desktop JVM probe measured", satisfiesAndroidBaselineCoverage = false),
    Pixel10ProXlAndroid16ProbeMeasured(
        "Pixel 10 Pro XL / Android 16 high-end runtime probe measured",
        satisfiesAndroidBaselineCoverage = false,
    ),
    AndroidBaselineCoverageMissing("Android baseline coverage missing", satisfiesAndroidBaselineCoverage = false),
    MidRangeAndroidCoverageMissing("mid-range Android coverage missing", satisfiesAndroidBaselineCoverage = false),
    LowEndAndroidCoverageMissing("low-end Android coverage missing", satisfiesAndroidBaselineCoverage = false),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability missing", satisfiesAndroidBaselineCoverage = false),
}

enum class Argon2idParameterApprovalBlocker(val label: String) {
    LowEndAndroidProbeMissing("low-end Android probe missing"),
    MidRangeAndroidProbeMissing("mid-range Android probe missing"),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability checks missing"),
    UnlockUxMeasurementMissing("lock-screen/unlock UX measurement missing"),
    BackgroundForegroundBehaviorMissing("background/foreground behavior checks missing"),
    AccessibilityTimeoutReviewMissing("accessibility/timeout policy review missing"),
    MemoryPressureFailureBehaviorMissing("memory-pressure failure behavior missing"),
    ProviderBoundaryKnownAnswerVectorsMissing("provider-boundary known-answer vectors missing"),
    ProductionKdfImplementationMissing("production KDF implementation missing"),
    SecureStorageStillDisabled("secure storage still disabled"),
    MainnetReleaseHardeningMissing("mainnet release-hardening review missing"),
}

enum class Argon2idFutureCalibrationRequirement(val label: String) {
    LowEndAndroidDeviceProbe("low-end Android device probe"),
    MidRangeAndroidDeviceProbe("mid-range Android device probe"),
    ThermalLoadRepeatabilityChecks("thermal/load repeatability checks"),
    LockScreenUnlockUxMeasurement("lock-screen/unlock UX measurement"),
    BackgroundForegroundBehaviorChecks("background/foreground behavior checks"),
    AccessibilityTimeoutPolicyReview("accessibility/timeout policy review"),
    MemoryPressureFailureBehavior("memory-pressure failure behavior"),
    PublicNonSecretFixturesOnly("public non-secret fixtures only"),
}

enum class Argon2idCalibrationRejectionReason(val label: String) {
    InvalidMemoryCost("invalid memory cost"),
    AmbiguousMemoryUnit("ambiguous memory unit"),
    InvalidPassCount("invalid pass count"),
    InvalidLaneCount("invalid lane count"),
    TooShortOutputLength("output length is too short"),
    UnsupportedKdfAlgorithm("unsupported KDF algorithm"),
    ProductionKdfDisabled("production KDF execution disabled"),
}

enum class Argon2idCalibrationWarning(val label: String) {
    ProbeOnlyNotProductionSetting("probe-only; not a production setting"),
    CandidateParameterPolicyNotFinal("candidate parameter policy is not final"),
    LowMemoryProbeCandidate("low-memory candidate is probe-only"),
    TooFastSettingWouldBeWeak("too-fast setting would be weak for production"),
    PixelEvidenceHighEndOnly("Pixel evidence covers high-end Android only"),
    AndroidBaselineCoverageMissing("Android baseline coverage is missing"),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability checks are missing"),
    AndroidDeviceVariance("Android device timing varies by RAM, CPU, thermal state, load, and OEM behavior"),
    TimingIsNotBenchmark("probe timing is not a stable benchmark"),
    MemoryZeroizationUnresolved("JVM/Android zeroization remains best-effort and unresolved"),
}

sealed interface Argon2idCalibrationPolicyResult<out T> {
    data class Accepted<T>(
        val value: T,
    ) : Argon2idCalibrationPolicyResult<T>

    data class Rejected(
        val reason: Argon2idCalibrationRejectionReason,
        val safeDetail: String,
    ) : Argon2idCalibrationPolicyResult<Nothing>
}

class Argon2idMemoryCost private constructor(
    val kib: Int,
) {
    val bytes: Long
        get() = kib.toLong() * 1024L

    val label: String
        get() = if (kib % Argon2idMemoryUnit.MiB.kibMultiplier == 0) {
            "${kib / Argon2idMemoryUnit.MiB.kibMultiplier} MiB"
        } else {
            "$kib KiB"
        }

    companion object {
        fun kib(value: Int): Argon2idCalibrationPolicyResult<Argon2idMemoryCost> =
            fromExplicit(value, Argon2idMemoryUnit.KiB)

        fun mib(value: Int): Argon2idCalibrationPolicyResult<Argon2idMemoryCost> =
            fromExplicit(value, Argon2idMemoryUnit.MiB)

        fun fromExplicit(
            value: Int,
            unit: Argon2idMemoryUnit,
        ): Argon2idCalibrationPolicyResult<Argon2idMemoryCost> =
            if (value <= 0) {
                Argon2idCalibrationPolicyResult.Rejected(
                    reason = Argon2idCalibrationRejectionReason.InvalidMemoryCost,
                    safeDetail = "Argon2id memory cost must be a positive explicit ${unit.label} value.",
                )
            } else {
                Argon2idCalibrationPolicyResult.Accepted(
                    Argon2idMemoryCost(kib = value * unit.kibMultiplier),
                )
            }

        fun fromExplicitLabel(label: String): Argon2idCalibrationPolicyResult<Argon2idMemoryCost> {
            val trimmed = label.trim()
            val match = Regex("""^([1-9][0-9]*)\s+(KiB|MiB)$""").matchEntire(trimmed)
                ?: return Argon2idCalibrationPolicyResult.Rejected(
                    reason = Argon2idCalibrationRejectionReason.AmbiguousMemoryUnit,
                    safeDetail = "Argon2id memory cost must use an explicit KiB or MiB unit.",
                )
            val value = match.groupValues[1].toInt()
            val unit = Argon2idMemoryUnit.valueOf(match.groupValues[2])
            return fromExplicit(value, unit)
        }
    }
}

class Argon2idPassCount private constructor(val value: Int) {
    companion object {
        fun of(value: Int): Argon2idCalibrationPolicyResult<Argon2idPassCount> =
            if (value <= 0) {
                Argon2idCalibrationPolicyResult.Rejected(
                    reason = Argon2idCalibrationRejectionReason.InvalidPassCount,
                    safeDetail = "Argon2id pass count must be positive.",
                )
            } else {
                Argon2idCalibrationPolicyResult.Accepted(Argon2idPassCount(value))
            }
    }
}

class Argon2idLaneCount private constructor(val value: Int) {
    companion object {
        fun of(value: Int): Argon2idCalibrationPolicyResult<Argon2idLaneCount> =
            if (value <= 0) {
                Argon2idCalibrationPolicyResult.Rejected(
                    reason = Argon2idCalibrationRejectionReason.InvalidLaneCount,
                    safeDetail = "Argon2id lane count must be positive.",
                )
            } else {
                Argon2idCalibrationPolicyResult.Accepted(Argon2idLaneCount(value))
            }
    }
}

class Argon2idOutputLength private constructor(val bytes: Int) {
    companion object {
        const val MINIMUM_VAULT_KDF_OUTPUT_BYTES: Int = 32

        fun ofBytes(value: Int): Argon2idCalibrationPolicyResult<Argon2idOutputLength> =
            if (value < MINIMUM_VAULT_KDF_OUTPUT_BYTES) {
                Argon2idCalibrationPolicyResult.Rejected(
                    reason = Argon2idCalibrationRejectionReason.TooShortOutputLength,
                    safeDetail = "Argon2id vault KDF output must be at least 32 bytes.",
                )
            } else {
                Argon2idCalibrationPolicyResult.Accepted(Argon2idOutputLength(value))
            }
    }
}

data class Argon2idParameterCandidate(
    val id: String,
    val memoryCost: Argon2idMemoryCost,
    val passes: Argon2idPassCount,
    val lanes: Argon2idLaneCount,
    val outputLength: Argon2idOutputLength,
    val version: Argon2idVersion,
    val platformClasses: Set<Argon2idCalibrationPlatformClass>,
    val targetLatencyBand: Argon2idTargetLatencyBand,
    val productionRecommendation: Boolean,
) {
    val label: String =
        "${memoryCost.label}, ${passes.value} passes, ${lanes.value} lane, ${outputLength.bytes}-byte output"
}

data class Argon2idParameterTier(
    val tier: Argon2idParameterTierKind,
    val candidate: Argon2idParameterCandidate?,
    val evidence: Set<Argon2idDeviceClassEvidenceStatus>,
    val note: String,
) {
    val finalProductionApproved: Boolean
        get() = tier.finalProductionApproved

    val universalAndroidPolicy: Boolean
        get() = tier.universalAndroidPolicy

    val candidateId: String
        get() = candidate?.id ?: "unresolved"
}

data class Argon2idCandidateParameterPolicy(
    val status: Argon2idParameterPolicyStatus,
    val tiers: List<Argon2idParameterTier>,
    val evidence: Set<Argon2idDeviceClassEvidenceStatus>,
    val minimumProbeFloorCandidateId: String,
    val minimumOutputLength: Argon2idOutputLength,
    val finalApprovalBlockers: Set<Argon2idParameterApprovalBlocker>,
    val futureCalibrationRequirements: Set<Argon2idFutureCalibrationRequirement>,
) {
    val finalProductionParametersApproved: Boolean
        get() = status.finalProductionApproved && finalApprovalBlockers.isEmpty()

    val productionKdfEnabled: Boolean
        get() = status.productionKdfEnabled

    val androidBaselineCoverageSatisfied: Boolean
        get() = evidence.any { it.satisfiesAndroidBaselineCoverage }

    fun tier(kind: Argon2idParameterTierKind): Argon2idParameterTier =
        tiers.single { it.tier == kind }
}

data class Argon2idCalibrationResultSummary(
    val candidateId: String,
    val platformClass: Argon2idCalibrationPlatformClass,
    val elapsedMillis: Long,
    val publicNonSecretFixture: Boolean,
    val persisted: Boolean,
    val finalProductionSetting: Boolean,
) {
    val safeSummary: String =
        "ARGON2ID_CALIBRATION_PROBE candidate=$candidateId platform=${platformClass.name} elapsedMs=$elapsedMillis persisted=$persisted finalProductionSetting=$finalProductionSetting"
}

data class Argon2idCalibrationPolicy(
    val status: Argon2idCalibrationImplementationStatus,
    val targetKdf: EncryptedVaultKdfAlgorithm,
    val version: Argon2idVersion,
    val fallbackKdf: EncryptedVaultKdfAlgorithm,
    val fallbackSelected: Boolean,
    val rejectedDefaultKdfs: Set<EncryptedVaultKdfAlgorithm>,
    val candidateParameters: List<Argon2idParameterCandidate>,
    val candidateParameterPolicy: Argon2idCandidateParameterPolicy,
    val warnings: Set<Argon2idCalibrationWarning>,
) {
    val productionKdfEnabled: Boolean
        get() = status.productionKdfEnabled

    val calibrationComplete: Boolean
        get() = status.calibrationComplete

    val desktopProbeCandidates: List<Argon2idParameterCandidate>
        get() = candidateParameters.filter {
            Argon2idCalibrationPlatformClass.LinuxDesktopJvm in it.platformClasses ||
                Argon2idCalibrationPlatformClass.DesktopExtendedProbe in it.platformClasses
        }

    val androidRuntimeProbeCandidates: List<Argon2idParameterCandidate>
        get() = candidateParameters.filter {
            Argon2idCalibrationPlatformClass.AndroidRuntime in it.platformClasses
        }

    fun acceptsProductionKdf(algorithm: EncryptedVaultKdfAlgorithm): Boolean =
        productionKdfEnabled && algorithm == targetKdf && algorithm !in rejectedDefaultKdfs

    fun fallbackIsSelected(algorithm: EncryptedVaultKdfAlgorithm): Boolean =
        fallbackSelected && algorithm == fallbackKdf

    fun evaluateAlgorithm(algorithm: EncryptedVaultKdfAlgorithm): Argon2idCalibrationPolicyResult<EncryptedVaultKdfAlgorithm> =
        when {
            !productionKdfEnabled -> Argon2idCalibrationPolicyResult.Rejected(
                reason = Argon2idCalibrationRejectionReason.ProductionKdfDisabled,
                safeDetail = "Production KDF execution remains disabled.",
            )
            algorithm != targetKdf -> Argon2idCalibrationPolicyResult.Rejected(
                reason = Argon2idCalibrationRejectionReason.UnsupportedKdfAlgorithm,
                safeDetail = "Only Argon2id is the current vault KDF target.",
            )
            else -> Argon2idCalibrationPolicyResult.Accepted(algorithm)
        }

    fun warningsFor(candidate: Argon2idParameterCandidate): Set<Argon2idCalibrationWarning> = buildSet {
        add(Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting)
        add(Argon2idCalibrationWarning.CandidateParameterPolicyNotFinal)
        add(Argon2idCalibrationWarning.TimingIsNotBenchmark)
        add(Argon2idCalibrationWarning.MemoryZeroizationUnresolved)
        if (candidate.memoryCost.kib < 32 * 1024) {
            add(Argon2idCalibrationWarning.LowMemoryProbeCandidate)
            add(Argon2idCalibrationWarning.TooFastSettingWouldBeWeak)
        }
        if (Argon2idCalibrationPlatformClass.AndroidRuntime in candidate.platformClasses) {
            add(Argon2idCalibrationWarning.AndroidDeviceVariance)
            add(Argon2idCalibrationWarning.PixelEvidenceHighEndOnly)
            add(Argon2idCalibrationWarning.AndroidBaselineCoverageMissing)
            add(Argon2idCalibrationWarning.ThermalLoadRepeatabilityMissing)
        }
    }

    companion object {
        fun currentProbeOnly(): Argon2idCalibrationPolicy {
            val candidates = listOf(
                candidate(
                    id = "argon2id-probe-16mib-2p-1lane",
                    memoryMiB = 16,
                    passes = 2,
                    lanes = 1,
                    platformClasses = setOf(
                        Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                        Argon2idCalibrationPlatformClass.AndroidRuntime,
                        Argon2idCalibrationPlatformClass.AndroidMemoryConstrained,
                    ),
                    targetLatencyBand = Argon2idTargetLatencyBand.SmokeProbeOnly,
                ),
                candidate(
                    id = "argon2id-probe-32mib-3p-1lane",
                    memoryMiB = 32,
                    passes = 3,
                    lanes = 1,
                    platformClasses = setOf(
                        Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                        Argon2idCalibrationPlatformClass.AndroidRuntime,
                    ),
                    targetLatencyBand = Argon2idTargetLatencyBand.InteractiveUnlockCandidate,
                ),
                candidate(
                    id = "argon2id-probe-64mib-3p-1lane",
                    memoryMiB = 64,
                    passes = 3,
                    lanes = 1,
                    platformClasses = setOf(
                        Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                        Argon2idCalibrationPlatformClass.DesktopExtendedProbe,
                    ),
                    targetLatencyBand = Argon2idTargetLatencyBand.InteractiveUnlockCandidate,
                ),
            )
            return Argon2idCalibrationPolicy(
                status = Argon2idCalibrationImplementationStatus.PolicyPresentProbeOnly,
                targetKdf = EncryptedVaultKdfAlgorithm.Argon2id,
                version = Argon2idVersion.Version19,
                fallbackKdf = EncryptedVaultKdfAlgorithm.Scrypt,
                fallbackSelected = false,
                rejectedDefaultKdfs = setOf(EncryptedVaultKdfAlgorithm.Pbkdf2),
                candidateParameters = candidates,
                candidateParameterPolicy = candidateParameterPolicy(candidates),
                warnings = setOf(
                    Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting,
                    Argon2idCalibrationWarning.CandidateParameterPolicyNotFinal,
                    Argon2idCalibrationWarning.AndroidDeviceVariance,
                    Argon2idCalibrationWarning.PixelEvidenceHighEndOnly,
                    Argon2idCalibrationWarning.AndroidBaselineCoverageMissing,
                    Argon2idCalibrationWarning.ThermalLoadRepeatabilityMissing,
                    Argon2idCalibrationWarning.TimingIsNotBenchmark,
                    Argon2idCalibrationWarning.MemoryZeroizationUnresolved,
                ),
            )
        }

        private fun candidate(
            id: String,
            memoryMiB: Int,
            passes: Int,
            lanes: Int,
            platformClasses: Set<Argon2idCalibrationPlatformClass>,
            targetLatencyBand: Argon2idTargetLatencyBand,
        ): Argon2idParameterCandidate =
            Argon2idParameterCandidate(
                id = id,
                memoryCost = Argon2idMemoryCost.mib(memoryMiB).acceptedValue(),
                passes = Argon2idPassCount.of(passes).acceptedValue(),
                lanes = Argon2idLaneCount.of(lanes).acceptedValue(),
                outputLength = Argon2idOutputLength.ofBytes(
                    Argon2idOutputLength.MINIMUM_VAULT_KDF_OUTPUT_BYTES,
                ).acceptedValue(),
                version = Argon2idVersion.Version19,
                platformClasses = platformClasses,
                targetLatencyBand = targetLatencyBand,
                productionRecommendation = false,
            )

        private fun candidateParameterPolicy(
            candidates: List<Argon2idParameterCandidate>,
        ): Argon2idCandidateParameterPolicy {
            val byId = candidates.associateBy { it.id }
            return Argon2idCandidateParameterPolicy(
                status = Argon2idParameterPolicyStatus.CandidatePolicyPresentNotFinal,
                tiers = listOf(
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.DesktopCandidate,
                        candidate = byId.getValue("argon2id-probe-64mib-3p-1lane"),
                        evidence = setOf(Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured),
                        note = "Candidate desktop tier from current desktop JVM probe evidence; not final.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.HighEndAndroidCandidate,
                        candidate = byId.getValue("argon2id-probe-32mib-3p-1lane"),
                        evidence = setOf(Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured),
                        note = "Candidate high-end Android tier from Pixel 10 Pro XL / Android 16 probe evidence; not universal Android policy.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.MobileFallbackProbeFloor,
                        candidate = byId.getValue("argon2id-probe-16mib-2p-1lane"),
                        evidence = setOf(
                            Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured,
                            Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
                        ),
                        note = "Minimum fallback/probe floor only; not the preferred production default.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.AndroidBaselineUnresolved,
                        candidate = null,
                        evidence = setOf(
                            Argon2idDeviceClassEvidenceStatus.AndroidBaselineCoverageMissing,
                            Argon2idDeviceClassEvidenceStatus.MidRangeAndroidCoverageMissing,
                            Argon2idDeviceClassEvidenceStatus.LowEndAndroidCoverageMissing,
                            Argon2idDeviceClassEvidenceStatus.ThermalLoadRepeatabilityMissing,
                        ),
                        note = "Universal Android baseline policy is unresolved until lower-end and mid-range device coverage is measured.",
                    ),
                ),
                evidence = setOf(
                    Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured,
                    Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
                ),
                minimumProbeFloorCandidateId = "argon2id-probe-16mib-2p-1lane",
                minimumOutputLength = Argon2idOutputLength.ofBytes(
                    Argon2idOutputLength.MINIMUM_VAULT_KDF_OUTPUT_BYTES,
                ).acceptedValue(),
                finalApprovalBlockers = Argon2idParameterApprovalBlocker.entries.toSet(),
                futureCalibrationRequirements = Argon2idFutureCalibrationRequirement.entries.toSet(),
            )
        }

        private fun <T> Argon2idCalibrationPolicyResult<T>.acceptedValue(): T =
            when (this) {
                is Argon2idCalibrationPolicyResult.Accepted -> value
                is Argon2idCalibrationPolicyResult.Rejected -> error("Invalid built-in Argon2id candidate: ${reason.name}")
            }
    }
}

fun commonArgon2idCalibrationPolicy(): Argon2idCalibrationPolicy =
    Argon2idCalibrationPolicy.currentProbeOnly()
