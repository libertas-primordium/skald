package com.libertasprimordium.skald.security

enum class VaultCryptoProviderCandidateId(val label: String) {
    DisabledFailClosed("disabled fail-closed provider"),
    TinkBouncyCastleSplit("Tink plus Bouncy Castle split-stack candidate"),
    LazysodiumJavaAndroid("Lazysodium Java/Android candidate"),
    IonSpinKmpLibsodium("IonSpin KMP libsodium candidate"),
}

enum class VaultCryptoProviderImplementationState(
    val label: String,
    val executableProviderPresent: Boolean,
    val testOnly: Boolean,
    val productionApproved: Boolean,
) {
    Disabled(
        label = "disabled provider",
        executableProviderPresent = true,
        testOnly = false,
        productionApproved = false,
    ),
    TestOnly(
        label = "test-only provider",
        executableProviderPresent = true,
        testOnly = true,
        productionApproved = false,
    ),
    ExecutableUnavailable(
        label = "executable provider unavailable",
        executableProviderPresent = false,
        testOnly = false,
        productionApproved = false,
    ),
    ExecutableBlocked(
        label = "executable provider blocked",
        executableProviderPresent = true,
        testOnly = false,
        productionApproved = false,
    ),
    ExecutableCandidate(
        label = "executable provider candidate",
        executableProviderPresent = true,
        testOnly = false,
        productionApproved = false,
    ),
    ProductionApproved(
        label = "production-approved provider",
        executableProviderPresent = true,
        testOnly = false,
        productionApproved = true,
    ),
}

enum class VaultCryptoProviderSelectionUse(
    val label: String,
    val productionPersistence: Boolean,
    val mainnetRequested: Boolean,
) {
    DisabledRuntimeFallback(
        label = "disabled runtime fallback",
        productionPersistence = false,
        mainnetRequested = false,
    ),
    ProductionPersistence(
        label = "production persistence",
        productionPersistence = true,
        mainnetRequested = false,
    ),
    MainnetProductionPersistence(
        label = "mainnet production persistence",
        productionPersistence = true,
        mainnetRequested = true,
    ),
}

enum class VaultCryptoProviderSelectionDecision(
    val label: String,
    val runtimeProviderSelected: Boolean,
    val productionProviderSelectable: Boolean,
) {
    DisabledProviderSelected(
        label = "disabled provider selected",
        runtimeProviderSelected = true,
        productionProviderSelectable = false,
    ),
    Blocked(
        label = "provider selection blocked",
        runtimeProviderSelected = false,
        productionProviderSelectable = false,
    ),
    Rejected(
        label = "provider candidate rejected",
        runtimeProviderSelected = false,
        productionProviderSelectable = false,
    ),
    Deferred(
        label = "provider candidate deferred",
        runtimeProviderSelected = false,
        productionProviderSelectable = false,
    ),
    Unavailable(
        label = "provider unavailable",
        runtimeProviderSelected = false,
        productionProviderSelectable = false,
    ),
}

enum class VaultCryptoProviderProductionApprovalGate(val label: String) {
    ProductionProviderImplementationExists("production provider implementation exists"),
    ProductionProviderLevelKatsPassed("production provider-level KATs passed"),
    AndroidAndDesktopRuntimeCoverage("Android and desktop runtime coverage exists"),
    Argon2idParametersFinalForPlatform("Argon2id parameters final for the platform"),
    DependencyLicenseReviewComplete("dependency and license review complete"),
    KeysetOrRawKeyHandlingApproved("keyset or raw-key handling approved"),
    SecureSecretStorageApproved("secure secret storage approved"),
    SecureMetadataStorageApproved("secure metadata storage approved"),
    VaultContainerStorageReviewComplete("vault container/storage review complete"),
    RedactionFailureModeTestsPassed("redaction and failure-mode tests passed"),
    MigrationCorruptionTestsPassed("migration and corruption tests passed"),
    MainnetReleaseHardeningApproved("mainnet release-hardening approved"),
}

data class VaultCryptoProviderProductionApprovalGateState(
    val gate: VaultCryptoProviderProductionApprovalGate,
    val satisfied: Boolean,
    val safeDetail: String,
)

enum class VaultCryptoProviderSelectionBlocker(val label: String) {
    ProviderDisabledByPolicy("provider disabled by policy"),
    ProductionProviderImplementationMissing("production provider implementation missing"),
    ProductionProviderLevelKatsMissing("production provider-level KATs missing"),
    DependencyLevelKatsInsufficientForSelection("dependency-level KATs are insufficient for selection"),
    TestProviderKatsInsufficientForSelection("test-provider KATs are insufficient for production selection"),
    AndroidCompatibilityRuntimeChecksMissing("Android compatibility runtime provider or randomness checks missing"),
    KdfParameterPolicyNotFinal("KDF parameter policy is not final"),
    TinkKeysetOrRawKeyHandlingUnapproved("Tink keyset or raw-key handling unapproved"),
    SecureSecretStorageDisabled("secure secret storage disabled"),
    SecureMetadataStorageDisabled("secure metadata storage disabled"),
    VaultContainerStorageReviewMissing("vault container/storage review missing"),
    RedactionFailureModeTestsMissing("redaction and failure-mode tests missing"),
    MigrationCorruptionTestsMissing("migration and corruption tests missing"),
    MainnetDisabled("mainnet disabled"),
    CandidateRejectedForCurrentVault("candidate rejected for current vault branch"),
    CandidateDeferred("candidate deferred"),
    CandidateUnavailable("candidate unavailable"),
    NativePackagingUnverified("native packaging unverified"),
    DependencyLicenseReviewIncomplete("dependency/license review incomplete"),
}

data class VaultCryptoProviderDependencyEvidence(
    val candidate: VaultCryptoDependencyCandidate?,
    val dependencyReviewComplete: Boolean,
    val licenseReviewComplete: Boolean,
    val packageReviewComplete: Boolean,
    val dependencyLevelKatsPassed: Boolean,
    val candidateOnly: Boolean,
    val nativePackagingAccepted: Boolean,
    val safeDetail: String,
)

data class VaultCryptoProviderKatEvidenceSummary(
    val dependencyLevelKatsPassed: Boolean,
    val testProviderKatsPassed: Boolean,
    val productionProviderKatsPassed: Boolean,
    val dependencyLevelKatsSatisfyProductionSelection: Boolean,
    val testProviderKatsSatisfyProductionSelection: Boolean,
    val safeDetail: String,
)

data class VaultCryptoProviderPlatformCoverageEvidence(
    val platform: EncryptedVaultPlatform,
    val desktopRuntimeKatsPassed: Boolean,
    val androidRuntimeKatsPassed: Boolean,
    val androidBaselineParameterPolicySatisfied: Boolean,
    val androidCompatibilityPlanningSatisfied: Boolean,
    val lowEndModelTestingRequired: Boolean,
    val midRangeModelTestingRequired: Boolean,
    val releaseLikeRuntimeCovered: Boolean,
    val safeDetail: String,
)

data class VaultCryptoProviderParameterPolicyEvidence(
    val argon2idVersion: Argon2idVersion,
    val desktopCandidatePolicyPresent: Boolean,
    val highEndAndroidCandidatePolicyPresent: Boolean,
    val androidBaselineCoverageSatisfied: Boolean,
    val finalParametersApproved: Boolean,
    val candidatePolicyOnly: Boolean,
    val safeDetail: String,
)

data class VaultCryptoProviderStorageReadinessEvidence(
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val vaultContainerStorageReviewComplete: Boolean,
    val productionPersistenceApproved: Boolean,
    val safeDetail: String,
)

data class VaultCryptoProviderEvidenceBundle(
    val dependencyEvidence: VaultCryptoProviderDependencyEvidence,
    val katEvidence: VaultCryptoProviderKatEvidenceSummary,
    val platformCoverage: VaultCryptoProviderPlatformCoverageEvidence,
    val parameterPolicy: VaultCryptoProviderParameterPolicyEvidence,
    val storageReadiness: VaultCryptoProviderStorageReadinessEvidence,
)

data class VaultCryptoProviderSelectionRequest(
    val requestedCandidate: VaultCryptoProviderCandidateId = VaultCryptoProviderCandidateId.DisabledFailClosed,
    val platform: EncryptedVaultPlatform = EncryptedVaultPlatform.LinuxDesktop,
    val use: VaultCryptoProviderSelectionUse = VaultCryptoProviderSelectionUse.DisabledRuntimeFallback,
    val requireUniversalAndroidParameterPolicy: Boolean = false,
    val requestMainnet: Boolean = false,
) {
    val mainnetRequested: Boolean
        get() = requestMainnet || use.mainnetRequested
}

data class VaultCryptoProviderSelectionCandidate(
    val id: VaultCryptoProviderCandidateId,
    val implementationState: VaultCryptoProviderImplementationState,
    val decision: VaultCryptoProviderSelectionDecision,
    val evidence: VaultCryptoProviderEvidenceBundle,
    val productionApprovalGates: List<VaultCryptoProviderProductionApprovalGateState>,
    val blockers: Set<VaultCryptoProviderSelectionBlocker>,
    val safeDetail: String,
) {
    val productionSelectable: Boolean
        get() = decision.productionProviderSelectable &&
            implementationState.productionApproved &&
            blockers.isEmpty() &&
            productionApprovalGates.all { it.satisfied }
}

data class VaultCryptoProviderSelectionResult(
    val request: VaultCryptoProviderSelectionRequest,
    val selectedCandidateId: VaultCryptoProviderCandidateId,
    val selectedProvider: VaultCryptoProvider,
    val selectedProviderIsDisabled: Boolean,
    val selectedProviderStatus: VaultCryptoProviderImplementationStatus,
    val requestedCandidate: VaultCryptoProviderSelectionCandidate,
    val candidates: List<VaultCryptoProviderSelectionCandidate>,
    val decision: VaultCryptoProviderSelectionDecision,
    val blockers: Set<VaultCryptoProviderSelectionBlocker>,
    val safeDetail: String,
) {
    val productionProviderSelectable: Boolean
        get() = candidates.any { it.productionSelectable }
}

object VaultCryptoProviderSelectionRegistry {
    fun select(
        request: VaultCryptoProviderSelectionRequest = VaultCryptoProviderSelectionRequest(),
        secureStorageCapability: SecureStorageCapability = commonDisabledSecureStorageCapability(),
        secureMetadataCapability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
        readiness: EncryptedVaultReadiness = commonDisabledEncryptedVaultReadiness(),
        argon2idPolicy: Argon2idCalibrationPolicy = commonArgon2idCalibrationPolicy(),
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment =
            commonAndroidVaultCompatibilityUnknownAssessment(),
    ): VaultCryptoProviderSelectionResult {
        val candidates = candidates(
            request = request,
            secureStorageCapability = secureStorageCapability,
            secureMetadataCapability = secureMetadataCapability,
            readiness = readiness,
            argon2idPolicy = argon2idPolicy,
            androidCompatibilityAssessment = androidCompatibilityAssessment,
        )
        val requestedCandidate = candidates.singleOrNull { it.id == request.requestedCandidate }
            ?: unavailableCandidate(request, secureStorageCapability, secureMetadataCapability, argon2idPolicy)
        val disabledProvider = DisabledVaultCryptoProvider()

        return VaultCryptoProviderSelectionResult(
            request = request,
            selectedCandidateId = VaultCryptoProviderCandidateId.DisabledFailClosed,
            selectedProvider = disabledProvider,
            selectedProviderIsDisabled = true,
            selectedProviderStatus = disabledProvider.statusReport.implementationStatus,
            requestedCandidate = requestedCandidate,
            candidates = candidates,
            decision = requestedCandidate.decision,
            blockers = requestedCandidate.blockers + selectionUseBlockers(request),
            safeDetail = "Provider selection is fail-closed. Runtime selection returns only the disabled provider; no executable provider crypto, vault storage, persistence, or mainnet path is available.",
        )
    }

    fun candidates(
        request: VaultCryptoProviderSelectionRequest = VaultCryptoProviderSelectionRequest(),
        secureStorageCapability: SecureStorageCapability = commonDisabledSecureStorageCapability(),
        secureMetadataCapability: SecureMetadataPersistenceCapability = commonDisabledSecureMetadataCapability(),
        readiness: EncryptedVaultReadiness = commonDisabledEncryptedVaultReadiness(),
        argon2idPolicy: Argon2idCalibrationPolicy = commonArgon2idCalibrationPolicy(),
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment =
            commonAndroidVaultCompatibilityUnknownAssessment(),
    ): List<VaultCryptoProviderSelectionCandidate> =
        listOf(
            disabledCandidate(
                request,
                secureStorageCapability,
                secureMetadataCapability,
                readiness,
                argon2idPolicy,
                androidCompatibilityAssessment,
            ),
            tinkBouncyCandidate(
                request,
                secureStorageCapability,
                secureMetadataCapability,
                readiness,
                argon2idPolicy,
                androidCompatibilityAssessment,
            ),
            lazysodiumCandidate(
                request,
                secureStorageCapability,
                secureMetadataCapability,
                argon2idPolicy,
                androidCompatibilityAssessment,
            ),
            ionSpinCandidate(
                request,
                secureStorageCapability,
                secureMetadataCapability,
                argon2idPolicy,
                androidCompatibilityAssessment,
            ),
        )

    private fun disabledCandidate(
        request: VaultCryptoProviderSelectionRequest,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        readiness: EncryptedVaultReadiness,
        argon2idPolicy: Argon2idCalibrationPolicy,
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment,
    ): VaultCryptoProviderSelectionCandidate {
        val blockers = setOf(
            VaultCryptoProviderSelectionBlocker.ProviderDisabledByPolicy,
            VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing,
            VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing,
        ) + storageBlockers(secureStorageCapability, secureMetadataCapability, readiness) +
            selectionUseBlockers(request)
        return VaultCryptoProviderSelectionCandidate(
            id = VaultCryptoProviderCandidateId.DisabledFailClosed,
            implementationState = VaultCryptoProviderImplementationState.Disabled,
            decision = VaultCryptoProviderSelectionDecision.DisabledProviderSelected,
            evidence = evidenceBundle(
                platform = request.platform,
                dependencyCandidate = null,
                dependencyReviewComplete = false,
                licenseReviewComplete = false,
                packageReviewComplete = false,
                dependencyLevelKatsPassed = false,
                testProviderKatsPassed = false,
                productionProviderKatsPassed = false,
                desktopRuntimeKatsPassed = false,
                androidRuntimeKatsPassed = false,
                nativePackagingAccepted = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = readiness,
                argon2idPolicy = argon2idPolicy,
                androidCompatibilityAssessment = androidCompatibilityAssessment,
                safeDetail = "Disabled provider is the only runtime-selectable provider and performs no crypto.",
            ),
            productionApprovalGates = productionApprovalGates(
                dependencyLicenseReviewComplete = false,
                runtimeCoverageComplete = false,
                finalParametersApproved = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = readiness,
                productionProviderImplementationExists = false,
                productionProviderKatsPassed = false,
                keyHandlingApproved = false,
                redactionFailureModeTestsPassed = false,
                migrationCorruptionTestsPassed = false,
                mainnetReleaseHardeningApproved = false,
            ),
            blockers = blockers,
            safeDetail = "Selected only as a disabled boundary. It is not a production crypto provider.",
        )
    }

    private fun tinkBouncyCandidate(
        request: VaultCryptoProviderSelectionRequest,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        readiness: EncryptedVaultReadiness,
        argon2idPolicy: Argon2idCalibrationPolicy,
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment,
    ): VaultCryptoProviderSelectionCandidate {
        val androidCompatibilityBlockers = if (
            request.platform == EncryptedVaultPlatform.Android ||
            request.requireUniversalAndroidParameterPolicy
        ) {
            if (androidCompatibilityAssessment.compatibilityPlanningSatisfied) {
                emptySet()
            } else {
                setOf(VaultCryptoProviderSelectionBlocker.AndroidCompatibilityRuntimeChecksMissing)
            }
        } else {
            emptySet()
        }
        val blockers = setOf(
            VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing,
            VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing,
            VaultCryptoProviderSelectionBlocker.DependencyLevelKatsInsufficientForSelection,
            VaultCryptoProviderSelectionBlocker.TestProviderKatsInsufficientForSelection,
            VaultCryptoProviderSelectionBlocker.KdfParameterPolicyNotFinal,
            VaultCryptoProviderSelectionBlocker.TinkKeysetOrRawKeyHandlingUnapproved,
            VaultCryptoProviderSelectionBlocker.RedactionFailureModeTestsMissing,
            VaultCryptoProviderSelectionBlocker.MigrationCorruptionTestsMissing,
        ) + androidCompatibilityBlockers +
            storageBlockers(secureStorageCapability, secureMetadataCapability, readiness) +
            selectionUseBlockers(request)
        return VaultCryptoProviderSelectionCandidate(
            id = VaultCryptoProviderCandidateId.TinkBouncyCastleSplit,
            implementationState = VaultCryptoProviderImplementationState.ExecutableUnavailable,
            decision = VaultCryptoProviderSelectionDecision.Blocked,
            evidence = evidenceBundle(
                platform = request.platform,
                dependencyCandidate = VaultCryptoDependencyCandidate.TinkBouncyCastleSplit,
                dependencyReviewComplete = true,
                licenseReviewComplete = true,
                packageReviewComplete = true,
                dependencyLevelKatsPassed = true,
                testProviderKatsPassed = true,
                productionProviderKatsPassed = false,
                desktopRuntimeKatsPassed = true,
                androidRuntimeKatsPassed = true,
                nativePackagingAccepted = true,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = readiness,
                argon2idPolicy = argon2idPolicy,
                androidCompatibilityAssessment = androidCompatibilityAssessment,
                safeDetail = "Tink plus Bouncy Castle has candidate-level dependency, runtime KAT, test-provider KAT, and package evidence, but no production provider exists.",
            ),
            productionApprovalGates = productionApprovalGates(
                dependencyLicenseReviewComplete = true,
                runtimeCoverageComplete = true,
                finalParametersApproved = argon2idPolicy.candidateParameterPolicy.finalProductionParametersApproved,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = readiness,
                productionProviderImplementationExists = false,
                productionProviderKatsPassed = false,
                keyHandlingApproved = false,
                redactionFailureModeTestsPassed = false,
                migrationCorruptionTestsPassed = false,
                mainnetReleaseHardeningApproved = false,
            ),
            blockers = blockers,
            safeDetail = "Listed as a future executable candidate only. It cannot be selected for production persistence.",
        )
    }

    private fun lazysodiumCandidate(
        request: VaultCryptoProviderSelectionRequest,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        argon2idPolicy: Argon2idCalibrationPolicy,
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment,
    ): VaultCryptoProviderSelectionCandidate {
        val blockers = setOf(
            VaultCryptoProviderSelectionBlocker.CandidateRejectedForCurrentVault,
            VaultCryptoProviderSelectionBlocker.NativePackagingUnverified,
            VaultCryptoProviderSelectionBlocker.DependencyLicenseReviewIncomplete,
            VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing,
            VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing,
        ) + storageBlockers(
            secureStorageCapability,
            secureMetadataCapability,
            commonDisabledEncryptedVaultReadiness(),
        ) + selectionUseBlockers(request)
        return VaultCryptoProviderSelectionCandidate(
            id = VaultCryptoProviderCandidateId.LazysodiumJavaAndroid,
            implementationState = VaultCryptoProviderImplementationState.ExecutableUnavailable,
            decision = VaultCryptoProviderSelectionDecision.Rejected,
            evidence = evidenceBundle(
                platform = request.platform,
                dependencyCandidate = VaultCryptoDependencyCandidate.LazysodiumJavaAndroid,
                dependencyReviewComplete = false,
                licenseReviewComplete = false,
                packageReviewComplete = false,
                dependencyLevelKatsPassed = false,
                testProviderKatsPassed = false,
                productionProviderKatsPassed = false,
                desktopRuntimeKatsPassed = false,
                androidRuntimeKatsPassed = false,
                nativePackagingAccepted = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                argon2idPolicy = argon2idPolicy,
                androidCompatibilityAssessment = androidCompatibilityAssessment,
                safeDetail = "Rejected for this vault branch after Android duplicate JNA class packaging failure.",
            ),
            productionApprovalGates = productionApprovalGates(
                dependencyLicenseReviewComplete = false,
                runtimeCoverageComplete = false,
                finalParametersApproved = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                productionProviderImplementationExists = false,
                productionProviderKatsPassed = false,
                keyHandlingApproved = false,
                redactionFailureModeTestsPassed = false,
                migrationCorruptionTestsPassed = false,
                mainnetReleaseHardeningApproved = false,
            ),
            blockers = blockers,
            safeDetail = "Rejected and non-selectable in the current vault branch.",
        )
    }

    private fun ionSpinCandidate(
        request: VaultCryptoProviderSelectionRequest,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        argon2idPolicy: Argon2idCalibrationPolicy,
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment,
    ): VaultCryptoProviderSelectionCandidate {
        val blockers = setOf(
            VaultCryptoProviderSelectionBlocker.CandidateDeferred,
            VaultCryptoProviderSelectionBlocker.NativePackagingUnverified,
            VaultCryptoProviderSelectionBlocker.DependencyLicenseReviewIncomplete,
            VaultCryptoProviderSelectionBlocker.ProductionProviderImplementationMissing,
            VaultCryptoProviderSelectionBlocker.ProductionProviderLevelKatsMissing,
        ) + storageBlockers(
            secureStorageCapability,
            secureMetadataCapability,
            commonDisabledEncryptedVaultReadiness(),
        ) + selectionUseBlockers(request)
        return VaultCryptoProviderSelectionCandidate(
            id = VaultCryptoProviderCandidateId.IonSpinKmpLibsodium,
            implementationState = VaultCryptoProviderImplementationState.ExecutableUnavailable,
            decision = VaultCryptoProviderSelectionDecision.Deferred,
            evidence = evidenceBundle(
                platform = request.platform,
                dependencyCandidate = VaultCryptoDependencyCandidate.IonSpinKmpLibsodium,
                dependencyReviewComplete = false,
                licenseReviewComplete = false,
                packageReviewComplete = false,
                dependencyLevelKatsPassed = false,
                testProviderKatsPassed = false,
                productionProviderKatsPassed = false,
                desktopRuntimeKatsPassed = false,
                androidRuntimeKatsPassed = false,
                nativePackagingAccepted = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                argon2idPolicy = argon2idPolicy,
                androidCompatibilityAssessment = androidCompatibilityAssessment,
                safeDetail = "Deferred after metadata/POM inspection only; package and runtime behavior are unverified.",
            ),
            productionApprovalGates = productionApprovalGates(
                dependencyLicenseReviewComplete = false,
                runtimeCoverageComplete = false,
                finalParametersApproved = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                productionProviderImplementationExists = false,
                productionProviderKatsPassed = false,
                keyHandlingApproved = false,
                redactionFailureModeTestsPassed = false,
                migrationCorruptionTestsPassed = false,
                mainnetReleaseHardeningApproved = false,
            ),
            blockers = blockers,
            safeDetail = "Deferred and non-selectable until a separate libsodium/KMP package and runtime probe branch.",
        )
    }

    private fun unavailableCandidate(
        request: VaultCryptoProviderSelectionRequest,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        argon2idPolicy: Argon2idCalibrationPolicy,
    ): VaultCryptoProviderSelectionCandidate =
        VaultCryptoProviderSelectionCandidate(
            id = request.requestedCandidate,
            implementationState = VaultCryptoProviderImplementationState.ExecutableUnavailable,
            decision = VaultCryptoProviderSelectionDecision.Unavailable,
            evidence = evidenceBundle(
                platform = request.platform,
                dependencyCandidate = null,
                dependencyReviewComplete = false,
                licenseReviewComplete = false,
                packageReviewComplete = false,
                dependencyLevelKatsPassed = false,
                testProviderKatsPassed = false,
                productionProviderKatsPassed = false,
                desktopRuntimeKatsPassed = false,
                androidRuntimeKatsPassed = false,
                nativePackagingAccepted = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                argon2idPolicy = argon2idPolicy,
                androidCompatibilityAssessment = commonAndroidVaultCompatibilityUnknownAssessment(),
                safeDetail = "Requested provider candidate is unavailable.",
            ),
            productionApprovalGates = productionApprovalGates(
                dependencyLicenseReviewComplete = false,
                runtimeCoverageComplete = false,
                finalParametersApproved = false,
                secureStorageCapability = secureStorageCapability,
                secureMetadataCapability = secureMetadataCapability,
                readiness = commonDisabledEncryptedVaultReadiness(),
                productionProviderImplementationExists = false,
                productionProviderKatsPassed = false,
                keyHandlingApproved = false,
                redactionFailureModeTestsPassed = false,
                migrationCorruptionTestsPassed = false,
                mainnetReleaseHardeningApproved = false,
            ),
            blockers = setOf(VaultCryptoProviderSelectionBlocker.CandidateUnavailable) +
                selectionUseBlockers(request),
            safeDetail = "Unavailable provider requests fail closed to the disabled provider.",
        )

    private fun evidenceBundle(
        platform: EncryptedVaultPlatform,
        dependencyCandidate: VaultCryptoDependencyCandidate?,
        dependencyReviewComplete: Boolean,
        licenseReviewComplete: Boolean,
        packageReviewComplete: Boolean,
        dependencyLevelKatsPassed: Boolean,
        testProviderKatsPassed: Boolean,
        productionProviderKatsPassed: Boolean,
        desktopRuntimeKatsPassed: Boolean,
        androidRuntimeKatsPassed: Boolean,
        nativePackagingAccepted: Boolean,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        readiness: EncryptedVaultReadiness,
        argon2idPolicy: Argon2idCalibrationPolicy,
        androidCompatibilityAssessment: AndroidVaultCompatibilityAssessment,
        safeDetail: String,
    ): VaultCryptoProviderEvidenceBundle =
        VaultCryptoProviderEvidenceBundle(
            dependencyEvidence = VaultCryptoProviderDependencyEvidence(
                candidate = dependencyCandidate,
                dependencyReviewComplete = dependencyReviewComplete,
                licenseReviewComplete = licenseReviewComplete,
                packageReviewComplete = packageReviewComplete,
                dependencyLevelKatsPassed = dependencyLevelKatsPassed,
                candidateOnly = true,
                nativePackagingAccepted = nativePackagingAccepted,
                safeDetail = safeDetail,
            ),
            katEvidence = VaultCryptoProviderKatEvidenceSummary(
                dependencyLevelKatsPassed = dependencyLevelKatsPassed,
                testProviderKatsPassed = testProviderKatsPassed,
                productionProviderKatsPassed = productionProviderKatsPassed,
                dependencyLevelKatsSatisfyProductionSelection = false,
                testProviderKatsSatisfyProductionSelection = false,
                safeDetail = "Dependency-level and test-provider KAT evidence remain insufficient for production provider selection.",
            ),
            platformCoverage = VaultCryptoProviderPlatformCoverageEvidence(
                platform = platform,
                desktopRuntimeKatsPassed = desktopRuntimeKatsPassed,
                androidRuntimeKatsPassed = androidRuntimeKatsPassed,
                androidBaselineParameterPolicySatisfied =
                    argon2idPolicy.candidateParameterPolicy.androidBaselineCoverageSatisfied,
                androidCompatibilityPlanningSatisfied =
                    androidCompatibilityAssessment.compatibilityPlanningSatisfied,
                lowEndModelTestingRequired = androidCompatibilityAssessment.lowEndModelTestingRequired,
                midRangeModelTestingRequired = androidCompatibilityAssessment.midRangeModelTestingRequired,
                releaseLikeRuntimeCovered = false,
                safeDetail = "Runtime evidence and Android compatibility planning do not replace production provider approval or release-like validation.",
            ),
            parameterPolicy = VaultCryptoProviderParameterPolicyEvidence(
                argon2idVersion = Argon2idVersion.Version19,
                desktopCandidatePolicyPresent =
                    argon2idPolicy.candidateParameterPolicy
                        .tier(Argon2idParameterTierKind.DesktopCandidate)
                        .candidate != null,
                highEndAndroidCandidatePolicyPresent =
                    argon2idPolicy.candidateParameterPolicy
                        .tier(Argon2idParameterTierKind.HighEndAndroidCandidate)
                        .candidate != null,
                androidBaselineCoverageSatisfied =
                    argon2idPolicy.candidateParameterPolicy.androidBaselineCoverageSatisfied,
                finalParametersApproved =
                    argon2idPolicy.candidateParameterPolicy.finalProductionParametersApproved,
                candidatePolicyOnly = true,
                safeDetail = "Argon2id policy is candidate-only and not final production approval.",
            ),
            storageReadiness = VaultCryptoProviderStorageReadinessEvidence(
                secureSecretStorageAvailable = secureStorageCapability.status.availableForSecretMaterial &&
                    secureStorageCapability.canStoreSecrets &&
                    secureStorageCapability.canReadSecrets,
                secureMetadataStorageAvailable = secureMetadataCapability.status.availableForSensitiveMetadata &&
                    secureMetadataCapability.canStoreMetadata &&
                    secureMetadataCapability.canReadMetadata,
                vaultContainerStorageReviewComplete = false,
                productionPersistenceApproved = readiness.productionPersistenceEnabled,
                safeDetail = "Storage readiness is disabled/fail-closed; provider selection cannot enable persistence.",
            ),
        )

    private fun productionApprovalGates(
        dependencyLicenseReviewComplete: Boolean,
        runtimeCoverageComplete: Boolean,
        finalParametersApproved: Boolean,
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        readiness: EncryptedVaultReadiness,
        productionProviderImplementationExists: Boolean,
        productionProviderKatsPassed: Boolean,
        keyHandlingApproved: Boolean,
        redactionFailureModeTestsPassed: Boolean,
        migrationCorruptionTestsPassed: Boolean,
        mainnetReleaseHardeningApproved: Boolean,
    ): List<VaultCryptoProviderProductionApprovalGateState> =
        listOf(
            gateState(
                VaultCryptoProviderProductionApprovalGate.ProductionProviderImplementationExists,
                productionProviderImplementationExists,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.ProductionProviderLevelKatsPassed,
                productionProviderKatsPassed,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.AndroidAndDesktopRuntimeCoverage,
                runtimeCoverageComplete,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.Argon2idParametersFinalForPlatform,
                finalParametersApproved,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.DependencyLicenseReviewComplete,
                dependencyLicenseReviewComplete,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.KeysetOrRawKeyHandlingApproved,
                keyHandlingApproved,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.SecureSecretStorageApproved,
                secureStorageCapability.status.availableForSecretMaterial &&
                    secureStorageCapability.canStoreSecrets &&
                    secureStorageCapability.canReadSecrets,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.SecureMetadataStorageApproved,
                secureMetadataCapability.status.availableForSensitiveMetadata &&
                    secureMetadataCapability.canStoreMetadata &&
                    secureMetadataCapability.canReadMetadata,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.VaultContainerStorageReviewComplete,
                readiness.productionPersistenceEnabled,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.RedactionFailureModeTestsPassed,
                redactionFailureModeTestsPassed,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.MigrationCorruptionTestsPassed,
                migrationCorruptionTestsPassed,
            ),
            gateState(
                VaultCryptoProviderProductionApprovalGate.MainnetReleaseHardeningApproved,
                mainnetReleaseHardeningApproved,
            ),
        )

    private fun gateState(
        gate: VaultCryptoProviderProductionApprovalGate,
        satisfied: Boolean,
    ): VaultCryptoProviderProductionApprovalGateState =
        VaultCryptoProviderProductionApprovalGateState(
            gate = gate,
            satisfied = satisfied,
            safeDetail = if (satisfied) {
                "Gate evidence recorded."
            } else {
                "Gate remains blocked."
            },
        )

    private fun storageBlockers(
        secureStorageCapability: SecureStorageCapability,
        secureMetadataCapability: SecureMetadataPersistenceCapability,
        readiness: EncryptedVaultReadiness,
    ): Set<VaultCryptoProviderSelectionBlocker> =
        buildSet {
            if (!secureStorageCapability.status.availableForSecretMaterial ||
                !secureStorageCapability.canStoreSecrets ||
                !secureStorageCapability.canReadSecrets
            ) {
                add(VaultCryptoProviderSelectionBlocker.SecureSecretStorageDisabled)
            }
            if (!secureMetadataCapability.status.availableForSensitiveMetadata ||
                !secureMetadataCapability.canStoreMetadata ||
                !secureMetadataCapability.canReadMetadata
            ) {
                add(VaultCryptoProviderSelectionBlocker.SecureMetadataStorageDisabled)
            }
            if (!readiness.productionPersistenceEnabled) {
                add(VaultCryptoProviderSelectionBlocker.VaultContainerStorageReviewMissing)
            }
        }

    private fun selectionUseBlockers(
        request: VaultCryptoProviderSelectionRequest,
    ): Set<VaultCryptoProviderSelectionBlocker> =
        buildSet {
            if (request.mainnetRequested) {
                add(VaultCryptoProviderSelectionBlocker.MainnetDisabled)
            }
        }
}
