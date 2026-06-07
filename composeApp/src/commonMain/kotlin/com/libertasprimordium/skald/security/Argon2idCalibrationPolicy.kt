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
    LowMemoryProbeCandidate("low-memory candidate is probe-only"),
    TooFastSettingWouldBeWeak("too-fast setting would be weak for production"),
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
        add(Argon2idCalibrationWarning.TimingIsNotBenchmark)
        add(Argon2idCalibrationWarning.MemoryZeroizationUnresolved)
        if (candidate.memoryCost.kib < 32 * 1024) {
            add(Argon2idCalibrationWarning.LowMemoryProbeCandidate)
            add(Argon2idCalibrationWarning.TooFastSettingWouldBeWeak)
        }
        if (Argon2idCalibrationPlatformClass.AndroidRuntime in candidate.platformClasses) {
            add(Argon2idCalibrationWarning.AndroidDeviceVariance)
        }
    }

    companion object {
        fun currentProbeOnly(): Argon2idCalibrationPolicy =
            Argon2idCalibrationPolicy(
                status = Argon2idCalibrationImplementationStatus.PolicyPresentProbeOnly,
                targetKdf = EncryptedVaultKdfAlgorithm.Argon2id,
                version = Argon2idVersion.Version19,
                fallbackKdf = EncryptedVaultKdfAlgorithm.Scrypt,
                fallbackSelected = false,
                rejectedDefaultKdfs = setOf(EncryptedVaultKdfAlgorithm.Pbkdf2),
                candidateParameters = listOf(
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
                ),
                warnings = setOf(
                    Argon2idCalibrationWarning.ProbeOnlyNotProductionSetting,
                    Argon2idCalibrationWarning.AndroidDeviceVariance,
                    Argon2idCalibrationWarning.TimingIsNotBenchmark,
                    Argon2idCalibrationWarning.MemoryZeroizationUnresolved,
                ),
            )

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

        private fun <T> Argon2idCalibrationPolicyResult<T>.acceptedValue(): T =
            when (this) {
                is Argon2idCalibrationPolicyResult.Accepted -> value
                is Argon2idCalibrationPolicyResult.Rejected -> error("Invalid built-in Argon2id candidate: ${reason.name}")
            }
    }
}

fun commonArgon2idCalibrationPolicy(): Argon2idCalibrationPolicy =
    Argon2idCalibrationPolicy.currentProbeOnly()
