package com.libertasprimordium.skald.security

enum class Argon2idCalibrationImplementationStatus(
    val label: String,
    val calibrationComplete: Boolean,
    val productionKdfEnabled: Boolean,
    val stillDisabledBuildingBlockImplemented: Boolean,
    val candidateSelectionImplemented: Boolean,
    val memoryFailureHandlingImplemented: Boolean,
) {
    PolicyPresentProbeOnly(
        label = "calibration policy present; probe only",
        calibrationComplete = false,
        productionKdfEnabled = false,
        stillDisabledBuildingBlockImplemented = false,
        candidateSelectionImplemented = false,
        memoryFailureHandlingImplemented = false,
    ),
    StillDisabledBuildingBlockImplemented(
        label = "calibration policy building block implemented and tested; still disabled",
        calibrationComplete = false,
        productionKdfEnabled = false,
        stillDisabledBuildingBlockImplemented = true,
        candidateSelectionImplemented = true,
        memoryFailureHandlingImplemented = true,
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
    SharedFloorPolicyImplementedStillDisabled(
        label = "shared v1 floor policy implemented; production calibration approval blocked",
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
        label = "shared minimum floor tier",
        finalProductionApproved = false,
        universalAndroidPolicy = false,
    ),
    AndroidSupportedCompatibilityPlanning(
        label = "Android supported-compatibility planning tier",
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
    AndroidSupportedCompatibilityPolicyModeled(
        "supported Android compatibility policy modeled",
        satisfiesAndroidBaselineCoverage = true,
    ),
    MidRangeAndroidCoverageMissing("mid-range Android coverage missing", satisfiesAndroidBaselineCoverage = false),
    LowEndAndroidCoverageMissing("low-end Android coverage missing", satisfiesAndroidBaselineCoverage = false),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability missing", satisfiesAndroidBaselineCoverage = false),
}

enum class Argon2idParameterApprovalBlocker(val label: String) {
    LowEndAndroidProbeMissing("low-end Android probe missing"),
    MidRangeAndroidProbeMissing("mid-range Android probe missing"),
    ThermalLoadRepeatabilityMissing("thermal/load repeatability checks missing"),
    AndroidSupportedCompatibilityReviewMissing("supported Android compatibility review missing"),
    RuntimeCryptoProviderCheckMissing("runtime crypto provider check missing"),
    RuntimeEntropyCheckMissing("runtime cryptographic randomness check missing"),
    ApprovedRandomnessSourceMissing("approved OS or provider cryptographic randomness source missing"),
    UnlockUxMeasurementMissing("lock-screen/unlock UX measurement missing"),
    BackgroundForegroundBehaviorMissing("background/foreground behavior checks missing"),
    AccessibilityTimeoutReviewMissing("accessibility/timeout policy review missing"),
    MemoryPressureFailureBehaviorMissing("memory-pressure failure behavior missing"),
    ProviderBoundaryKnownAnswerVectorsMissing("provider-boundary known-answer vectors missing"),
    ProductionKdfImplementationMissing("production KDF implementation missing"),
    FinalProductionCalibrationApprovalMissing("final production calibration approval missing"),
    SecureStorageStillDisabled("secure storage still disabled"),
    MainnetReleaseHardeningMissing("mainnet release-hardening review missing"),
}

enum class Argon2idFutureCalibrationRequirement(val label: String) {
    AndroidManualEvidenceCaptureProtocol("manual Android calibration evidence capture protocol"),
    AndroidSupportedCompatibilityPolicyReview("supported Android compatibility policy review"),
    AndroidRuntimeProviderPrimitiveChecks("Android runtime provider and primitive checks"),
    AndroidRuntimeEntropyPathCheck("Android runtime cryptographic randomness path check"),
    VaultCreationFailClosedWarningReview("vault-creation fail-closed warning review"),
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
    MemoryBelowV1Floor("memory below Skald Vault v1 floor"),
    IterationsBelowV1Floor("iterations below Skald Vault v1 floor"),
    UnsupportedParallelism("unsupported parallelism"),
    SaltTooShort("salt too short"),
    UnsupportedOutputLength("unsupported output length"),
    UnsupportedArgon2Type("unsupported Argon2 type"),
    UnsupportedArgon2Version("unsupported Argon2 version"),
    InvalidElapsedMillis("invalid elapsed milliseconds"),
    CalibrationFailedAtFloor("calibration failed at the required floor"),
    CalibrationExecutionFailed("calibration execution failed"),
    StoredParametersUnsupportedOnDevice("stored parameters unsupported on this device"),
    DowngradeNotAllowed("silent parameter downgrade is not allowed"),
    NoSuccessfulCandidateAtOrAboveFloor("no successful calibration candidate at or above the v1 floor"),
}

enum class Argon2idCalibrationWarning(val label: String) {
    ProbeOnlyNotProductionSetting("probe-only; not a production setting"),
    CandidateParameterPolicyNotFinal("candidate parameter policy is not final"),
    LowMemoryProbeCandidate("low-memory candidate is probe-only"),
    TooFastSettingWouldBeWeak("too-fast setting would be weak for production"),
    PixelEvidenceHighEndOnly("Pixel evidence covers high-end Android only"),
    AndroidCompatibilityRuntimeChecksRequired("Android compatibility requires runtime provider and randomness checks"),
    ThermalLoadRepeatabilityDesirable("thermal/load repeatability checks remain desirable"),
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

enum class SkaldVaultV1Argon2idCalibrationParameterStrength(val label: String) {
    Floor("v1 shared minimum floor"),
    StrongerThanFloor("stronger than the v1 shared floor"),
}

enum class SkaldVaultV1Argon2idCalibrationLatencyClass(val label: String) {
    PreferredAtOrBelowOneSecond("preferred target at or below about 1 second"),
    AcceptableAtOrBelowTwoSeconds("acceptable target at or below about 2 seconds"),
    ExceedsAcceptableTarget("exceeds the acceptable target"),
}

sealed interface SkaldVaultV1Argon2idCalibrationResult<out T> {
    data class Accepted<T>(
        val value: T,
    ) : SkaldVaultV1Argon2idCalibrationResult<T>

    data class Rejected(
        val reason: Argon2idCalibrationRejectionReason,
        val safeMessage: String,
    ) : SkaldVaultV1Argon2idCalibrationResult<Nothing>
}

data class SkaldVaultV1Argon2idValidatedParameters(
    val parameters: SkaldVaultV1Argon2idParameters,
    val saltLengthBytes: Int,
    val strength: SkaldVaultV1Argon2idCalibrationParameterStrength,
    val existingStoredParametersAuthoritative: Boolean,
    val downgradeAllowed: Boolean,
)

data class SkaldVaultV1Argon2idCalibrationObservation(
    val candidateId: String,
    val parameters: SkaldVaultV1Argon2idParameters,
    val saltLengthBytes: Int,
    val elapsedMillis: Long,
    val executionSucceeded: Boolean,
)

data class SkaldVaultV1Argon2idSelectedCalibrationCandidate(
    val candidateId: String,
    val parameters: SkaldVaultV1Argon2idParameters,
    val saltLengthBytes: Int,
    val elapsedMillis: Long,
    val strength: SkaldVaultV1Argon2idCalibrationParameterStrength,
    val latencyClass: SkaldVaultV1Argon2idCalibrationLatencyClass,
    val selectedBelowFloor: Boolean,
    val selectedToForceSubOneSecond: Boolean,
    val productionKdfEnabled: Boolean,
)

data class SkaldVaultV1Argon2idStoredParameterAssessment(
    val parameters: SkaldVaultV1Argon2idParameters,
    val saltLengthBytes: Int,
    val authoritative: Boolean,
    val unlockAllowed: Boolean,
    val downgradeAttempted: Boolean,
    val safeUserMessage: String?,
)

data class SkaldVaultV1Argon2idCalibrationPolicyEvidence(
    val implementationStatus: Argon2idCalibrationImplementationStatus,
    val minimumMemoryMiB: Int,
    val minimumIterations: Int,
    val requiredParallelism: Int,
    val minimumSaltBytes: Int,
    val preferredNewVaultSaltBytes: Int,
    val outputRootMaterialBytes: Int,
    val preferredUnlockMillis: Int,
    val acceptableUnlockMillis: Int,
    val twoSecondsIsFailureCondition: Boolean,
    val weakenToForceSubOneSecondAllowed: Boolean,
    val existingStoredParametersAuthoritative: Boolean,
    val silentDowngradeAllowed: Boolean,
    val productionKdfEnabled: Boolean,
)

object SkaldVaultV1Argon2idCalibrationPolicy {
    const val POLICY_ID: String = "skald-vault-v1-argon2id-calibration-policy-v1"
    const val MINIMUM_MEMORY_MIB: Int = 64
    const val MINIMUM_MEMORY_KIB: Int = MINIMUM_MEMORY_MIB * 1024
    const val MINIMUM_ITERATIONS: Int = 3
    const val REQUIRED_PARALLELISM: Int = 1
    const val MINIMUM_SALT_BYTES: Int = 16
    const val PREFERRED_NEW_VAULT_SALT_BYTES: Int = 32
    const val OUTPUT_ROOT_MATERIAL_BYTES: Int = 64
    const val PREFERRED_UNLOCK_MILLIS: Int = 1_000
    const val ACCEPTABLE_UNLOCK_MILLIS: Int = 2_000

    val evidence: SkaldVaultV1Argon2idCalibrationPolicyEvidence =
        SkaldVaultV1Argon2idCalibrationPolicyEvidence(
            implementationStatus = Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented,
            minimumMemoryMiB = MINIMUM_MEMORY_MIB,
            minimumIterations = MINIMUM_ITERATIONS,
            requiredParallelism = REQUIRED_PARALLELISM,
            minimumSaltBytes = MINIMUM_SALT_BYTES,
            preferredNewVaultSaltBytes = PREFERRED_NEW_VAULT_SALT_BYTES,
            outputRootMaterialBytes = OUTPUT_ROOT_MATERIAL_BYTES,
            preferredUnlockMillis = PREFERRED_UNLOCK_MILLIS,
            acceptableUnlockMillis = ACCEPTABLE_UNLOCK_MILLIS,
            twoSecondsIsFailureCondition = false,
            weakenToForceSubOneSecondAllowed = false,
            existingStoredParametersAuthoritative = true,
            silentDowngradeAllowed = false,
            productionKdfEnabled = false,
        )

    fun validateCreationParameters(
        parameters: SkaldVaultV1Argon2idParameters,
        saltLengthBytes: Int,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idValidatedParameters> =
        validateParameters(parameters = parameters, saltLengthBytes = saltLengthBytes, stored = false)

    fun validateStoredVaultParameters(
        parameters: SkaldVaultV1Argon2idParameters,
        saltLengthBytes: Int,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idValidatedParameters> =
        validateParameters(parameters = parameters, saltLengthBytes = saltLengthBytes, stored = true)

    fun selectCandidate(
        observations: List<SkaldVaultV1Argon2idCalibrationObservation>,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idSelectedCalibrationCandidate> {
        val validSuccessful = observations.mapNotNull { observation ->
            val validated = when (
                val result = validateCreationParameters(
                    parameters = observation.parameters,
                    saltLengthBytes = observation.saltLengthBytes,
                )
            ) {
                is SkaldVaultV1Argon2idCalibrationResult.Accepted -> result.value
                is SkaldVaultV1Argon2idCalibrationResult.Rejected -> return@mapNotNull null
            }
            if (!observation.executionSucceeded) return@mapNotNull null
            if (observation.elapsedMillis <= 0) return@mapNotNull null
            CandidateWithValidation(observation = observation, validated = validated)
        }
        if (validSuccessful.isEmpty()) {
            val floorFailed = observations.any { observation ->
                observation.isFloorCandidate() && !observation.executionSucceeded
            }
            return rejected(
                reason = if (floorFailed) {
                    Argon2idCalibrationRejectionReason.CalibrationFailedAtFloor
                } else {
                    Argon2idCalibrationRejectionReason.NoSuccessfulCandidateAtOrAboveFloor
                },
            )
        }

        val acceptable = validSuccessful.filter {
            it.observation.elapsedMillis <= ACCEPTABLE_UNLOCK_MILLIS
        }
        val selected = (acceptable.ifEmpty { validSuccessful }).maxWith(
            compareBy<CandidateWithValidation> { it.validated.parameters.memoryKiB }
                .thenBy { it.validated.parameters.iterations }
                .thenByDescending { it.observation.elapsedMillis },
        )
        return SkaldVaultV1Argon2idCalibrationResult.Accepted(
            SkaldVaultV1Argon2idSelectedCalibrationCandidate(
                candidateId = selected.observation.candidateId,
                parameters = selected.validated.parameters,
                saltLengthBytes = selected.validated.saltLengthBytes,
                elapsedMillis = selected.observation.elapsedMillis,
                strength = selected.validated.strength,
                latencyClass = selected.observation.elapsedMillis.toLatencyClass(),
                selectedBelowFloor = false,
                selectedToForceSubOneSecond = false,
                productionKdfEnabled = false,
            ),
        )
    }

    fun assessCreationExecution(
        observation: SkaldVaultV1Argon2idCalibrationObservation,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idSelectedCalibrationCandidate> {
        val validated = when (
            val result = validateCreationParameters(
                parameters = observation.parameters,
                saltLengthBytes = observation.saltLengthBytes,
            )
        ) {
            is SkaldVaultV1Argon2idCalibrationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idCalibrationResult.Rejected -> return result
        }
        if (observation.elapsedMillis <= 0) {
            return rejected(Argon2idCalibrationRejectionReason.InvalidElapsedMillis)
        }
        if (!observation.executionSucceeded) {
            return rejected(
                if (validated.strength == SkaldVaultV1Argon2idCalibrationParameterStrength.Floor) {
                    Argon2idCalibrationRejectionReason.CalibrationFailedAtFloor
                } else {
                    Argon2idCalibrationRejectionReason.CalibrationExecutionFailed
                },
            )
        }
        return SkaldVaultV1Argon2idCalibrationResult.Accepted(
            SkaldVaultV1Argon2idSelectedCalibrationCandidate(
                candidateId = observation.candidateId,
                parameters = validated.parameters,
                saltLengthBytes = validated.saltLengthBytes,
                elapsedMillis = observation.elapsedMillis,
                strength = validated.strength,
                latencyClass = observation.elapsedMillis.toLatencyClass(),
                selectedBelowFloor = false,
                selectedToForceSubOneSecond = false,
                productionKdfEnabled = false,
            ),
        )
    }

    fun assessStoredVaultExecution(
        parameters: SkaldVaultV1Argon2idParameters,
        saltLengthBytes: Int,
        executionSucceeded: Boolean,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idStoredParameterAssessment> {
        val validated = when (
            val result = validateStoredVaultParameters(
                parameters = parameters,
                saltLengthBytes = saltLengthBytes,
            )
        ) {
            is SkaldVaultV1Argon2idCalibrationResult.Accepted -> result.value
            is SkaldVaultV1Argon2idCalibrationResult.Rejected -> return result
        }
        if (!executionSucceeded) {
            return SkaldVaultV1Argon2idCalibrationResult.Rejected(
                reason = Argon2idCalibrationRejectionReason.StoredParametersUnsupportedOnDevice,
                safeMessage = "Stored Skald Vault v1 Argon2id parameters could not execute on this device; unlock must fail closed without downgrade.",
            )
        }
        return SkaldVaultV1Argon2idCalibrationResult.Accepted(
            SkaldVaultV1Argon2idStoredParameterAssessment(
                parameters = validated.parameters,
                saltLengthBytes = validated.saltLengthBytes,
                authoritative = true,
                unlockAllowed = true,
                downgradeAttempted = false,
                safeUserMessage = null,
            ),
        )
    }

    fun rejectDowngradeAttempt(): SkaldVaultV1Argon2idCalibrationResult<Nothing> =
        rejected(Argon2idCalibrationRejectionReason.DowngradeNotAllowed)

    private fun validateParameters(
        parameters: SkaldVaultV1Argon2idParameters,
        saltLengthBytes: Int,
        stored: Boolean,
    ): SkaldVaultV1Argon2idCalibrationResult<SkaldVaultV1Argon2idValidatedParameters> {
        val reason = when {
            parameters.type != SkaldVaultV1Argon2idType.Argon2id ->
                Argon2idCalibrationRejectionReason.UnsupportedArgon2Type
            parameters.version != SkaldVaultV1Argon2idRootDerivation.ARGON2_VERSION_19 ->
                Argon2idCalibrationRejectionReason.UnsupportedArgon2Version
            parameters.memoryKiB < MINIMUM_MEMORY_KIB ->
                Argon2idCalibrationRejectionReason.MemoryBelowV1Floor
            parameters.iterations < MINIMUM_ITERATIONS ->
                Argon2idCalibrationRejectionReason.IterationsBelowV1Floor
            parameters.parallelism != REQUIRED_PARALLELISM ->
                Argon2idCalibrationRejectionReason.UnsupportedParallelism
            saltLengthBytes < MINIMUM_SALT_BYTES ->
                Argon2idCalibrationRejectionReason.SaltTooShort
            parameters.outputBytes != OUTPUT_ROOT_MATERIAL_BYTES ->
                Argon2idCalibrationRejectionReason.UnsupportedOutputLength
            else -> null
        }
        if (reason != null) return rejected(reason)

        val strength =
            if (parameters.memoryKiB == MINIMUM_MEMORY_KIB && parameters.iterations == MINIMUM_ITERATIONS) {
                SkaldVaultV1Argon2idCalibrationParameterStrength.Floor
            } else {
                SkaldVaultV1Argon2idCalibrationParameterStrength.StrongerThanFloor
            }
        return SkaldVaultV1Argon2idCalibrationResult.Accepted(
            SkaldVaultV1Argon2idValidatedParameters(
                parameters = parameters,
                saltLengthBytes = saltLengthBytes,
                strength = strength,
                existingStoredParametersAuthoritative = stored,
                downgradeAllowed = false,
            ),
        )
    }

    private fun SkaldVaultV1Argon2idCalibrationObservation.isFloorCandidate(): Boolean =
        parameters.type == SkaldVaultV1Argon2idType.Argon2id &&
            parameters.version == SkaldVaultV1Argon2idRootDerivation.ARGON2_VERSION_19 &&
            parameters.memoryKiB == MINIMUM_MEMORY_KIB &&
            parameters.iterations == MINIMUM_ITERATIONS &&
            parameters.parallelism == REQUIRED_PARALLELISM &&
            saltLengthBytes >= MINIMUM_SALT_BYTES &&
            parameters.outputBytes == OUTPUT_ROOT_MATERIAL_BYTES

    private fun Long.toLatencyClass(): SkaldVaultV1Argon2idCalibrationLatencyClass =
        when {
            this <= PREFERRED_UNLOCK_MILLIS ->
                SkaldVaultV1Argon2idCalibrationLatencyClass.PreferredAtOrBelowOneSecond
            this <= ACCEPTABLE_UNLOCK_MILLIS ->
                SkaldVaultV1Argon2idCalibrationLatencyClass.AcceptableAtOrBelowTwoSeconds
            else ->
                SkaldVaultV1Argon2idCalibrationLatencyClass.ExceedsAcceptableTarget
        }

    private fun rejected(
        reason: Argon2idCalibrationRejectionReason,
    ): SkaldVaultV1Argon2idCalibrationResult.Rejected =
        SkaldVaultV1Argon2idCalibrationResult.Rejected(
            reason = reason,
            safeMessage = "Skald Vault v1 Argon2id calibration rejected: ${reason.label}.",
        )

    private data class CandidateWithValidation(
        val observation: SkaldVaultV1Argon2idCalibrationObservation,
        val validated: SkaldVaultV1Argon2idValidatedParameters,
    )
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
    MidRangeEvidenceOptional("mid-range Android evidence optional"),
    LowEndEvidenceOptional("low-end Android evidence optional"),
    ReleaseLikeEvidenceDesirable("release-like Android evidence desirable"),
    ThermalLoadRepeatabilityDesirable("thermal/load repeatability evidence desirable"),
    SupportedAndroidCompatibilityPolicyModeled("supported Android compatibility policy modeled"),
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

    val compatibilityPlanningSatisfied: Boolean
        get() = androidBaselineSatisfied

    val exhaustiveDevicePerformanceProven: Boolean
        get() = highEndEvidencePresent &&
            midRangeEvidencePresent &&
            lowEndEvidencePresent &&
            releaseLikeEvidencePresent &&
            thermalLoadRepeatabilityPresent

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
                gate(AndroidArgon2idBaselineAcceptanceGate.MidRangeEvidenceOptional, true),
                gate(AndroidArgon2idBaselineAcceptanceGate.LowEndEvidenceOptional, true),
                gate(AndroidArgon2idBaselineAcceptanceGate.ReleaseLikeEvidenceDesirable, true),
                gate(
                    AndroidArgon2idBaselineAcceptanceGate.ThermalLoadRepeatabilityDesirable,
                    true,
                ),
                gate(
                    AndroidArgon2idBaselineAcceptanceGate.SupportedAndroidCompatibilityPolicyModeled,
                    true,
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
        if (candidate.memoryCost.kib < SkaldVaultV1Argon2idCalibrationPolicy.MINIMUM_MEMORY_KIB) {
            add(Argon2idCalibrationWarning.LowMemoryProbeCandidate)
            add(Argon2idCalibrationWarning.TooFastSettingWouldBeWeak)
        }
        if (Argon2idCalibrationPlatformClass.AndroidRuntime in candidate.platformClasses) {
            add(Argon2idCalibrationWarning.AndroidDeviceVariance)
            add(Argon2idCalibrationWarning.PixelEvidenceHighEndOnly)
            add(Argon2idCalibrationWarning.AndroidCompatibilityRuntimeChecksRequired)
            add(Argon2idCalibrationWarning.ThermalLoadRepeatabilityDesirable)
        }
    }

    companion object {
        fun currentProbeOnly(): Argon2idCalibrationPolicy {
            val candidates = listOf(
                candidate(
                    id = "argon2id-v1-floor-64mib-t3-p1-root64",
                    memoryMiB = 64,
                    passes = 3,
                    lanes = 1,
                    platformClasses = setOf(
                        Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                        Argon2idCalibrationPlatformClass.AndroidRuntime,
                    ),
                    targetLatencyBand = Argon2idTargetLatencyBand.InteractiveUnlockCandidate,
                ),
                candidate(
                    id = "argon2id-v1-desktop-stronger-96mib-t3-p1-root64",
                    memoryMiB = 96,
                    passes = 3,
                    lanes = 1,
                    platformClasses = setOf(
                        Argon2idCalibrationPlatformClass.LinuxDesktopJvm,
                        Argon2idCalibrationPlatformClass.DesktopExtendedProbe,
                    ),
                    targetLatencyBand = Argon2idTargetLatencyBand.ExtendedDesktopProbe,
                ),
            )
            return Argon2idCalibrationPolicy(
                status = Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented,
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
                    Argon2idCalibrationWarning.AndroidCompatibilityRuntimeChecksRequired,
                    Argon2idCalibrationWarning.ThermalLoadRepeatabilityDesirable,
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
                    SkaldVaultV1Argon2idCalibrationPolicy.OUTPUT_ROOT_MATERIAL_BYTES,
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
                status = Argon2idParameterPolicyStatus.SharedFloorPolicyImplementedStillDisabled,
                tiers = listOf(
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.DesktopCandidate,
                        candidate = byId.getValue("argon2id-v1-desktop-stronger-96mib-t3-p1-root64"),
                        evidence = setOf(Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured),
                        note = "Desktop may target stronger parameters than the shared floor after bounded calibration; not final production approval.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.HighEndAndroidCandidate,
                        candidate = byId.getValue("argon2id-v1-floor-64mib-t3-p1-root64"),
                        evidence = setOf(Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured),
                        note = "Android shares the 64 MiB / t=3 / p=1 v1 minimum floor; high-end timing evidence is not final approval.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.MobileFallbackProbeFloor,
                        candidate = byId.getValue("argon2id-v1-floor-64mib-t3-p1-root64"),
                        evidence = setOf(
                            Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured,
                            Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
                        ),
                        note = "Shared v1 minimum floor. Calibration may select stronger parameters, but it may not go below this floor.",
                    ),
                    Argon2idParameterTier(
                        tier = Argon2idParameterTierKind.AndroidSupportedCompatibilityPlanning,
                        candidate = null,
                        evidence = setOf(
                            Argon2idDeviceClassEvidenceStatus.AndroidSupportedCompatibilityPolicyModeled,
                        ),
                        note = "Android compatibility planning uses the supported OS baseline, runtime crypto/randomness checks, and fail-closed vault creation gates rather than mandatory low-end or mid-range model testing.",
                    ),
                ),
                evidence = setOf(
                    Argon2idDeviceClassEvidenceStatus.DesktopJvmProbeMeasured,
                    Argon2idDeviceClassEvidenceStatus.Pixel10ProXlAndroid16ProbeMeasured,
                    Argon2idDeviceClassEvidenceStatus.AndroidSupportedCompatibilityPolicyModeled,
                ),
                minimumProbeFloorCandidateId = "argon2id-v1-floor-64mib-t3-p1-root64",
                minimumOutputLength = Argon2idOutputLength.ofBytes(
                    SkaldVaultV1Argon2idCalibrationPolicy.OUTPUT_ROOT_MATERIAL_BYTES,
                ).acceptedValue(),
                finalApprovalBlockers = setOf(
                    Argon2idParameterApprovalBlocker.AndroidSupportedCompatibilityReviewMissing,
                    Argon2idParameterApprovalBlocker.RuntimeCryptoProviderCheckMissing,
                    Argon2idParameterApprovalBlocker.RuntimeEntropyCheckMissing,
                    Argon2idParameterApprovalBlocker.ApprovedRandomnessSourceMissing,
                    Argon2idParameterApprovalBlocker.UnlockUxMeasurementMissing,
                    Argon2idParameterApprovalBlocker.BackgroundForegroundBehaviorMissing,
                    Argon2idParameterApprovalBlocker.AccessibilityTimeoutReviewMissing,
                    Argon2idParameterApprovalBlocker.ProviderBoundaryKnownAnswerVectorsMissing,
                    Argon2idParameterApprovalBlocker.FinalProductionCalibrationApprovalMissing,
                    Argon2idParameterApprovalBlocker.SecureStorageStillDisabled,
                    Argon2idParameterApprovalBlocker.MainnetReleaseHardeningMissing,
                ),
                futureCalibrationRequirements = setOf(
                    Argon2idFutureCalibrationRequirement.AndroidManualEvidenceCaptureProtocol,
                    Argon2idFutureCalibrationRequirement.AndroidSupportedCompatibilityPolicyReview,
                    Argon2idFutureCalibrationRequirement.AndroidRuntimeProviderPrimitiveChecks,
                    Argon2idFutureCalibrationRequirement.AndroidRuntimeEntropyPathCheck,
                    Argon2idFutureCalibrationRequirement.VaultCreationFailClosedWarningReview,
                    Argon2idFutureCalibrationRequirement.ThermalLoadRepeatabilityChecks,
                    Argon2idFutureCalibrationRequirement.LockScreenUnlockUxMeasurement,
                    Argon2idFutureCalibrationRequirement.BackgroundForegroundBehaviorChecks,
                    Argon2idFutureCalibrationRequirement.AccessibilityTimeoutPolicyReview,
                    Argon2idFutureCalibrationRequirement.MemoryPressureFailureBehavior,
                    Argon2idFutureCalibrationRequirement.PublicNonSecretFixturesOnly,
                ),
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
