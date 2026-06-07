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
    AndroidManualEvidenceCaptureProtocol("manual Android calibration evidence capture protocol"),
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

enum class AndroidArgon2idDeviceClass(val label: String) {
    LowEnd("low-end Android"),
    MidRange("mid-range Android"),
    HighEnd("high-end Android"),
    Unknown("unknown Android device class"),
}

enum class AndroidArgon2idBuildProfile(
    val label: String,
    val releaseLikeEvidence: Boolean,
) {
    DebugInstrumented("debug/instrumented test runtime", releaseLikeEvidence = false),
    ReleaseLikeManual("release-like manual runtime", releaseLikeEvidence = true),
    Unknown("unknown build profile", releaseLikeEvidence = false),
}

enum class AndroidArgon2idCalibrationEvidenceRejectionReason(val label: String) {
    MissingRequiredField("missing required field"),
    InvalidApiLevel("invalid Android API level"),
    AmbiguousMemoryUnit("ambiguous memory unit"),
    MemoryUnitMustBeMib("Android evidence memory must be recorded in MiB"),
    InvalidElapsedMillis("invalid elapsed milliseconds"),
    InvalidRunCount("invalid run count"),
    InvalidRepeatedTiming("invalid repeated-run timing summary"),
    InvalidCalibrationRun("invalid calibration run"),
    SecretLikeFieldRejected("secret-like or personal-device field rejected"),
}

enum class AndroidArgon2idBaselineAcceptanceGate(val label: String) {
    HighEndEvidencePresent("high-end Android evidence present"),
    MidRangeEvidencePresent("mid-range Android evidence present"),
    LowEndEvidencePresent("low-end Android evidence present"),
    ReleaseLikeEvidencePresent("release-like Android evidence present"),
    ThermalLoadRepeatabilityPresent("thermal/load repeatability evidence present"),
    PublicNonSecretFixturesOnly("public non-secret fixtures only"),
    ManualEvidenceDoesNotApproveProductionKdf("manual evidence does not approve production KDF"),
}

enum class AndroidArgon2idBaselineBlocker(val label: String) {
    HighEndEvidenceMissing("high-end Android evidence missing"),
    MidRangeEvidenceMissing("mid-range Android evidence missing"),
    LowEndEvidenceMissing("low-end Android evidence missing"),
    ReleaseLikeEvidenceMissing("release-like Android evidence missing"),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability evidence missing"),
    NonSecretFixtureEvidenceMissing("public non-secret fixture evidence missing"),
    ManualEvidenceCannotApproveProductionKdf("manual evidence cannot approve production KDF"),
}

sealed interface AndroidArgon2idCalibrationEvidenceResult<out T> {
    data class Accepted<T>(
        val value: T,
    ) : AndroidArgon2idCalibrationEvidenceResult<T>

    data class Rejected(
        val reason: AndroidArgon2idCalibrationEvidenceRejectionReason,
        val safeDetail: String,
    ) : AndroidArgon2idCalibrationEvidenceResult<Nothing>
}

class AndroidArgon2idRuntimeEnvironment private constructor(
    val androidVersion: String,
    val apiLevel: Int,
    val manufacturer: String?,
    val model: String?,
    val buildProfile: AndroidArgon2idBuildProfile,
    val thermalStateNote: String,
    val foregroundBackgroundNote: String,
    val batteryChargingNote: String,
    val memoryPressureNote: String,
) {
    val releaseLikeEvidence: Boolean
        get() = buildProfile.releaseLikeEvidence

    companion object {
        fun recorded(
            androidVersion: String,
            apiLevel: Int,
            manufacturer: String?,
            model: String?,
            buildProfile: AndroidArgon2idBuildProfile,
            thermalStateNote: String,
            foregroundBackgroundNote: String,
            batteryChargingNote: String,
            memoryPressureNote: String,
        ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idRuntimeEnvironment> {
            val fields = listOf(
                "androidVersion" to androidVersion,
                "manufacturer" to manufacturer,
                "model" to model,
                "thermalStateNote" to thermalStateNote,
                "foregroundBackgroundNote" to foregroundBackgroundNote,
                "batteryChargingNote" to batteryChargingNote,
                "memoryPressureNote" to memoryPressureNote,
            )
            fields.firstOrNull { (name, value) ->
                name != "manufacturer" && name != "model" && value.isNullOrBlank()
            }?.let { (name, _) ->
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.MissingRequiredField,
                    safeDetail = "Android calibration evidence field '$name' must be recorded.",
                )
            }
            if (apiLevel <= 0) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidApiLevel,
                    safeDetail = "Android calibration evidence API level must be positive.",
                )
            }
            val secretLike = fields.firstOrNull { (_, value) ->
                value != null && value.containsAndroidCalibrationSecretLikeText()
            }
            if (secretLike != null) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.SecretLikeFieldRejected,
                    safeDetail = "Android calibration evidence must not record secrets, labels, credentials, addresses, serials, or personal device identifiers.",
                )
            }
            return AndroidArgon2idCalibrationEvidenceResult.Accepted(
                AndroidArgon2idRuntimeEnvironment(
                    androidVersion = androidVersion.trim(),
                    apiLevel = apiLevel,
                    manufacturer = manufacturer?.trim()?.ifEmpty { null },
                    model = model?.trim()?.ifEmpty { null },
                    buildProfile = buildProfile,
                    thermalStateNote = thermalStateNote.trim(),
                    foregroundBackgroundNote = foregroundBackgroundNote.trim(),
                    batteryChargingNote = batteryChargingNote.trim(),
                    memoryPressureNote = memoryPressureNote.trim(),
                ),
            )
        }
    }
}

class AndroidArgon2idCalibrationRunResult private constructor(
    val version: Argon2idVersion,
    val memoryCost: Argon2idMemoryCost,
    val passes: Argon2idPassCount,
    val lanes: Argon2idLaneCount,
    val outputLength: Argon2idOutputLength,
    val elapsedMillis: Long,
    val runCount: Int,
    val minElapsedMillis: Long?,
    val medianElapsedMillis: Long?,
    val maxElapsedMillis: Long?,
    val failureReason: String?,
) {
    val memoryMiB: Int
        get() = memoryCost.kib / Argon2idMemoryUnit.MiB.kibMultiplier

    val successful: Boolean
        get() = failureReason == null

    companion object {
        fun recorded(
            memoryLabel: String,
            passes: Int,
            lanes: Int,
            outputBytes: Int,
            elapsedMillis: Long,
            runCount: Int,
            minElapsedMillis: Long? = null,
            medianElapsedMillis: Long? = null,
            maxElapsedMillis: Long? = null,
            failureReason: String? = null,
            version: Argon2idVersion = Argon2idVersion.Version19,
        ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idCalibrationRunResult> {
            val memory = when (val result = Argon2idMemoryCost.fromExplicitLabel(memoryLabel)) {
                is Argon2idCalibrationPolicyResult.Accepted -> result.value
                is Argon2idCalibrationPolicyResult.Rejected -> {
                    return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                        reason = AndroidArgon2idCalibrationEvidenceRejectionReason.AmbiguousMemoryUnit,
                        safeDetail = "Android Argon2id calibration evidence must record memory with an explicit MiB unit.",
                    )
                }
            }
            if (!memoryLabel.trim().endsWith("MiB")) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.MemoryUnitMustBeMib,
                    safeDetail = "Android Argon2id calibration evidence must use MiB, not KiB.",
                )
            }
            return recorded(
                memoryCost = memory,
                passes = passes,
                lanes = lanes,
                outputBytes = outputBytes,
                elapsedMillis = elapsedMillis,
                runCount = runCount,
                minElapsedMillis = minElapsedMillis,
                medianElapsedMillis = medianElapsedMillis,
                maxElapsedMillis = maxElapsedMillis,
                failureReason = failureReason,
                version = version,
            )
        }

        fun recorded(
            memoryMiB: Int,
            passes: Int,
            lanes: Int,
            outputBytes: Int,
            elapsedMillis: Long,
            runCount: Int,
            minElapsedMillis: Long? = null,
            medianElapsedMillis: Long? = null,
            maxElapsedMillis: Long? = null,
            failureReason: String? = null,
            version: Argon2idVersion = Argon2idVersion.Version19,
        ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idCalibrationRunResult> {
            val memory = when (val result = Argon2idMemoryCost.mib(memoryMiB)) {
                is Argon2idCalibrationPolicyResult.Accepted -> result.value
                is Argon2idCalibrationPolicyResult.Rejected -> {
                    return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                        reason = AndroidArgon2idCalibrationEvidenceRejectionReason.AmbiguousMemoryUnit,
                        safeDetail = "Android Argon2id calibration evidence memory must be a positive MiB value.",
                    )
                }
            }
            return recorded(
                memoryCost = memory,
                passes = passes,
                lanes = lanes,
                outputBytes = outputBytes,
                elapsedMillis = elapsedMillis,
                runCount = runCount,
                minElapsedMillis = minElapsedMillis,
                medianElapsedMillis = medianElapsedMillis,
                maxElapsedMillis = maxElapsedMillis,
                failureReason = failureReason,
                version = version,
            )
        }

        private fun recorded(
            memoryCost: Argon2idMemoryCost,
            passes: Int,
            lanes: Int,
            outputBytes: Int,
            elapsedMillis: Long,
            runCount: Int,
            minElapsedMillis: Long?,
            medianElapsedMillis: Long?,
            maxElapsedMillis: Long?,
            failureReason: String?,
            version: Argon2idVersion,
        ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idCalibrationRunResult> {
            val passCount = when (val result = Argon2idPassCount.of(passes)) {
                is Argon2idCalibrationPolicyResult.Accepted -> result.value
                is Argon2idCalibrationPolicyResult.Rejected -> {
                    return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                        reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidCalibrationRun,
                        safeDetail = "Android Argon2id calibration evidence pass count must be positive.",
                    )
                }
            }
            val laneCount = when (val result = Argon2idLaneCount.of(lanes)) {
                is Argon2idCalibrationPolicyResult.Accepted -> result.value
                is Argon2idCalibrationPolicyResult.Rejected -> {
                    return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                        reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidCalibrationRun,
                        safeDetail = "Android Argon2id calibration evidence lane count must be positive.",
                    )
                }
            }
            val outputLength = when (val result = Argon2idOutputLength.ofBytes(outputBytes)) {
                is Argon2idCalibrationPolicyResult.Accepted -> result.value
                is Argon2idCalibrationPolicyResult.Rejected -> {
                    return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                        reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidCalibrationRun,
                        safeDetail = "Android Argon2id calibration evidence output length must be at least 32 bytes.",
                    )
                }
            }
            if (elapsedMillis <= 0) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidElapsedMillis,
                    safeDetail = "Android Argon2id calibration evidence elapsed time must be positive.",
                )
            }
            if (runCount <= 0) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidRunCount,
                    safeDetail = "Android Argon2id calibration evidence run count must be positive.",
                )
            }
            if (!repeatedTimingIsValid(runCount, minElapsedMillis, medianElapsedMillis, maxElapsedMillis)) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidRepeatedTiming,
                    safeDetail = "Android Argon2id calibration evidence repeated timing summary must be positive and ordered.",
                )
            }
            if (failureReason != null && failureReason.containsAndroidCalibrationSecretLikeText()) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.SecretLikeFieldRejected,
                    safeDetail = "Android Argon2id calibration evidence failure reason must be redacted and non-secret.",
                )
            }
            return AndroidArgon2idCalibrationEvidenceResult.Accepted(
                AndroidArgon2idCalibrationRunResult(
                    version = version,
                    memoryCost = memoryCost,
                    passes = passCount,
                    lanes = laneCount,
                    outputLength = outputLength,
                    elapsedMillis = elapsedMillis,
                    runCount = runCount,
                    minElapsedMillis = minElapsedMillis,
                    medianElapsedMillis = medianElapsedMillis,
                    maxElapsedMillis = maxElapsedMillis,
                    failureReason = failureReason?.trim()?.ifEmpty { null },
                ),
            )
        }

        private fun repeatedTimingIsValid(
            runCount: Int,
            minElapsedMillis: Long?,
            medianElapsedMillis: Long?,
            maxElapsedMillis: Long?,
        ): Boolean {
            val repeatedValues = listOf(minElapsedMillis, medianElapsedMillis, maxElapsedMillis)
            if (repeatedValues.all { it == null }) return runCount == 1
            if (runCount == 1) return false
            if (repeatedValues.any { it == null || it <= 0 }) return false
            val min = minElapsedMillis ?: return false
            val median = medianElapsedMillis ?: return false
            val max = maxElapsedMillis ?: return false
            return min <= median && median <= max
        }
    }
}

class AndroidArgon2idCalibrationEvidence private constructor(
    val deviceClass: AndroidArgon2idDeviceClass,
    val runtimeEnvironment: AndroidArgon2idRuntimeEnvironment,
    val runResults: List<AndroidArgon2idCalibrationRunResult>,
    val publicNonSecretFixture: Boolean,
    val thermalLoadRepeatabilityChecked: Boolean,
    val evidenceNote: String,
) {
    val releaseLikeEvidence: Boolean
        get() = runtimeEnvironment.releaseLikeEvidence

    val productionKdfApproved: Boolean = false

    val successfulRunResults: List<AndroidArgon2idCalibrationRunResult>
        get() = runResults.filter { it.successful }

    companion object {
        fun recorded(
            deviceClass: AndroidArgon2idDeviceClass,
            runtimeEnvironment: AndroidArgon2idRuntimeEnvironment,
            runResults: List<AndroidArgon2idCalibrationRunResult>,
            publicNonSecretFixture: Boolean,
            thermalLoadRepeatabilityChecked: Boolean,
            evidenceNote: String,
        ): AndroidArgon2idCalibrationEvidenceResult<AndroidArgon2idCalibrationEvidence> {
            if (runResults.isEmpty()) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.InvalidCalibrationRun,
                    safeDetail = "Android calibration evidence must include at least one Argon2id run result.",
                )
            }
            if (evidenceNote.isBlank()) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.MissingRequiredField,
                    safeDetail = "Android calibration evidence note must be recorded.",
                )
            }
            if (evidenceNote.containsAndroidCalibrationSecretLikeText()) {
                return AndroidArgon2idCalibrationEvidenceResult.Rejected(
                    reason = AndroidArgon2idCalibrationEvidenceRejectionReason.SecretLikeFieldRejected,
                    safeDetail = "Android calibration evidence note must not contain secrets, labels, credentials, addresses, or personal device identifiers.",
                )
            }
            return AndroidArgon2idCalibrationEvidenceResult.Accepted(
                AndroidArgon2idCalibrationEvidence(
                    deviceClass = deviceClass,
                    runtimeEnvironment = runtimeEnvironment,
                    runResults = runResults,
                    publicNonSecretFixture = publicNonSecretFixture,
                    thermalLoadRepeatabilityChecked = thermalLoadRepeatabilityChecked,
                    evidenceNote = evidenceNote.trim(),
                ),
            )
        }
    }
}

data class AndroidArgon2idBaselineGateState(
    val gate: AndroidArgon2idBaselineAcceptanceGate,
    val satisfied: Boolean,
    val safeDetail: String,
)

data class AndroidArgon2idBaselineAssessment(
    val highEndEvidencePresent: Boolean,
    val midRangeEvidencePresent: Boolean,
    val lowEndEvidencePresent: Boolean,
    val releaseLikeEvidencePresent: Boolean,
    val thermalLoadRepeatabilityPresent: Boolean,
    val publicNonSecretFixtureEvidencePresent: Boolean,
    val productionKdfApproved: Boolean,
    val blockers: Set<AndroidArgon2idBaselineBlocker>,
    val gates: List<AndroidArgon2idBaselineGateState>,
) {
    val androidBaselineSatisfied: Boolean
        get() = blockers.isEmpty()

    val finalProductionParametersApproved: Boolean = false
}

object AndroidArgon2idCalibrationEvidencePolicy {
    fun currentPixel10ProXlAndroid16HighEndEvidence(): AndroidArgon2idCalibrationEvidence =
        AndroidArgon2idCalibrationEvidence.recorded(
            deviceClass = AndroidArgon2idDeviceClass.HighEnd,
            runtimeEnvironment = AndroidArgon2idRuntimeEnvironment.recorded(
                androidVersion = "Android 16",
                apiLevel = 36,
                manufacturer = "Google",
                model = "Pixel 10 Pro XL",
                buildProfile = AndroidArgon2idBuildProfile.DebugInstrumented,
                thermalStateNote = "manual note: no thermal repeatability series recorded",
                foregroundBackgroundNote = "manual note: foreground instrumented test runtime",
                batteryChargingNote = "manual note: charging state not recorded",
                memoryPressureNote = "manual note: memory pressure not recorded",
            ).acceptedAndroidEvidenceValue(),
            runResults = listOf(
                AndroidArgon2idCalibrationRunResult.recorded(
                    memoryMiB = 16,
                    passes = 2,
                    lanes = 1,
                    outputBytes = 32,
                    elapsedMillis = 321,
                    runCount = 1,
                ).acceptedAndroidEvidenceValue(),
                AndroidArgon2idCalibrationRunResult.recorded(
                    memoryMiB = 32,
                    passes = 3,
                    lanes = 1,
                    outputBytes = 32,
                    elapsedMillis = 707,
                    runCount = 1,
                ).acceptedAndroidEvidenceValue(),
            ),
            publicNonSecretFixture = true,
            thermalLoadRepeatabilityChecked = false,
            evidenceNote = "Pixel 10 Pro XL Android 16 debug instrumented calibration evidence; high-end class only.",
        ).acceptedAndroidEvidenceValue()

    fun assess(
        evidence: List<AndroidArgon2idCalibrationEvidence> =
            listOf(currentPixel10ProXlAndroid16HighEndEvidence()),
    ): AndroidArgon2idBaselineAssessment {
        val successfulEvidence = evidence.filter { it.successfulRunResults.isNotEmpty() }
        val highEndEvidencePresent = successfulEvidence.any {
            it.deviceClass == AndroidArgon2idDeviceClass.HighEnd
        }
        val midRangeEvidencePresent = successfulEvidence.any {
            it.deviceClass == AndroidArgon2idDeviceClass.MidRange
        }
        val lowEndEvidencePresent = successfulEvidence.any {
            it.deviceClass == AndroidArgon2idDeviceClass.LowEnd
        }
        val releaseLikeEvidencePresent = successfulEvidence.any { it.releaseLikeEvidence }
        val thermalLoadRepeatabilityPresent = successfulEvidence.any {
            it.thermalLoadRepeatabilityChecked
        }
        val publicNonSecretFixtureEvidencePresent = successfulEvidence.isNotEmpty() &&
            successfulEvidence.all { it.publicNonSecretFixture }

        val blockers = buildSet {
            if (!highEndEvidencePresent) add(AndroidArgon2idBaselineBlocker.HighEndEvidenceMissing)
            if (!midRangeEvidencePresent) add(AndroidArgon2idBaselineBlocker.MidRangeEvidenceMissing)
            if (!lowEndEvidencePresent) add(AndroidArgon2idBaselineBlocker.LowEndEvidenceMissing)
            if (!releaseLikeEvidencePresent) add(AndroidArgon2idBaselineBlocker.ReleaseLikeEvidenceMissing)
            if (!thermalLoadRepeatabilityPresent) {
                add(AndroidArgon2idBaselineBlocker.ThermalLoadRepeatabilityMissing)
            }
            if (!publicNonSecretFixtureEvidencePresent) {
                add(AndroidArgon2idBaselineBlocker.NonSecretFixtureEvidenceMissing)
            }
        }

        return AndroidArgon2idBaselineAssessment(
            highEndEvidencePresent = highEndEvidencePresent,
            midRangeEvidencePresent = midRangeEvidencePresent,
            lowEndEvidencePresent = lowEndEvidencePresent,
            releaseLikeEvidencePresent = releaseLikeEvidencePresent,
            thermalLoadRepeatabilityPresent = thermalLoadRepeatabilityPresent,
            publicNonSecretFixtureEvidencePresent = publicNonSecretFixtureEvidencePresent,
            productionKdfApproved = false,
            blockers = blockers,
            gates = listOf(
                gate(AndroidArgon2idBaselineAcceptanceGate.HighEndEvidencePresent, highEndEvidencePresent),
                gate(AndroidArgon2idBaselineAcceptanceGate.MidRangeEvidencePresent, midRangeEvidencePresent),
                gate(AndroidArgon2idBaselineAcceptanceGate.LowEndEvidencePresent, lowEndEvidencePresent),
                gate(AndroidArgon2idBaselineAcceptanceGate.ReleaseLikeEvidencePresent, releaseLikeEvidencePresent),
                gate(
                    AndroidArgon2idBaselineAcceptanceGate.ThermalLoadRepeatabilityPresent,
                    thermalLoadRepeatabilityPresent,
                ),
                gate(
                    AndroidArgon2idBaselineAcceptanceGate.PublicNonSecretFixturesOnly,
                    publicNonSecretFixtureEvidencePresent,
                ),
                gate(
                    AndroidArgon2idBaselineAcceptanceGate.ManualEvidenceDoesNotApproveProductionKdf,
                    true,
                ),
            ),
        )
    }

    private fun gate(
        gate: AndroidArgon2idBaselineAcceptanceGate,
        satisfied: Boolean,
    ): AndroidArgon2idBaselineGateState =
        AndroidArgon2idBaselineGateState(
            gate = gate,
            satisfied = satisfied,
            safeDetail = if (satisfied) {
                "Gate evidence recorded."
            } else {
                "Gate remains blocked."
            },
        )

    private fun <T> AndroidArgon2idCalibrationEvidenceResult<T>.acceptedAndroidEvidenceValue(): T =
        when (this) {
            is AndroidArgon2idCalibrationEvidenceResult.Accepted -> value
            is AndroidArgon2idCalibrationEvidenceResult.Rejected ->
                error("Invalid built-in Android Argon2id calibration evidence: ${reason.name}")
        }
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

private fun String.containsAndroidCalibrationSecretLikeText(): Boolean {
    val lower = lowercase()
    val forbiddenFragments = listOf(
        "passphrase",
        "password",
        "mnemonic",
        "seed",
        "private",
        "xprv",
        "tprv",
        "wif",
        "nsec",
        "token",
        "macaroon",
        "credential",
        "cashu proof",
        "rpc cookie",
        "wallet label",
        "utxo label",
        "transaction note",
        "serial",
        "imei",
        "android_id",
        "android id",
    )
    return forbiddenFragments.any { it in lower } ||
        Regex("""\b(?:bc1|tb1|bcrt1)[a-z0-9]{20,}\b""").containsMatchIn(lower) ||
        Regex("""\b[0-9a-f]{64}\b""").containsMatchIn(lower)
}
