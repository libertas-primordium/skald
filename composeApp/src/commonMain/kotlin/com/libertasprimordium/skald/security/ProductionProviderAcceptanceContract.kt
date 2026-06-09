package com.libertasprimordium.skald.security

enum class ProductionProviderSuiteModel(
    val label: String,
    val providerAgilityAllowedInV1: Boolean,
) {
    SinglePinnedSuite(
        label = "single pinned production provider suite",
        providerAgilityAllowedInV1 = false,
    ),
}

enum class ProductionProviderPrimitiveRole(val label: String) {
    Kdf("key derivation function"),
    Aead("record AEAD"),
    RuntimeRandomness("runtime cryptographic randomness"),
}

data class ProductionProviderPrimitiveIdentity(
    val role: ProductionProviderPrimitiveRole,
    val implementation: String,
    val algorithm: String,
    val versionOrTemplate: String,
    val artifact: String?,
)

data class ProductionProviderSuiteIdentity(
    val suiteId: String,
    val model: ProductionProviderSuiteModel,
    val kdf: ProductionProviderPrimitiveIdentity,
    val aead: ProductionProviderPrimitiveIdentity,
    val runtimeRandomness: ProductionProviderPrimitiveIdentity,
    val pinnedArtifacts: Set<String>,
)

enum class ProductionProviderAcceptanceGate(val label: String) {
    ExactProviderSuitePinned("exact provider suite id and dependency versions pinned"),
    Argon2idPolicyApproved("Argon2id algorithm, version, and parameter policy approved"),
    Argon2idKatsPassed("Argon2id known-answer tests pass"),
    Argon2idCalibrationAndMemoryFailureApproved("Argon2id calibration bounds and memory-failure behavior approved"),
    XChaCha20Poly1305PrimitivePinned("XChaCha20-Poly1305 primitive/template/version pinned"),
    AeadKatsPassed("AEAD known-answer tests pass"),
    VaultKeyCommitmentHeaderAuthenticationImplemented("vault-level key commitment and header authentication implemented"),
    AeadAadPolicyApproved("AEAD AAD policy approved"),
    TamperTestsPassed("tamper tests cover header, ciphertext, nonce, tag, AAD, record metadata, and provider-suite metadata"),
    RuntimeOsSecureRandomEvidenceApproved("OS SecureRandom runtime provider/algorithm evidence approved"),
    UnknownRandomnessProviderStateRejected("unknown randomness/provider state rejected"),
    ForbiddenRandomApisGuarded("forbidden language and ad hoc random APIs guarded"),
    SecureSecretStorageReviewed("secure secret storage reviewed"),
    SecureMetadataStorageReviewed("secure metadata storage reviewed"),
    CrashCorruptionPartialWriteReviewed("crash, corruption, and partial-write behavior reviewed"),
    RedactionLeakageChecksPassed("redaction, logging, and crash-report leakage checks pass"),
    AndroidOptionalWrappingSeparateFromPassphrase("Android optional wrapping remains separate from passphrase recovery"),
    ProductionProviderImplementationExists("production provider implementation exists"),
    ReleaseReadinessExcludesDebugTestProviders("release readiness excludes debug and test-only providers"),
}

enum class ProductionProviderAcceptanceEvidenceState(
    val label: String,
    val satisfiesGate: Boolean,
) {
    Satisfied("satisfied", satisfiesGate = true),
    Missing("missing", satisfiesGate = false),
    Failed("failed", satisfiesGate = false),
    Unknown("unknown", satisfiesGate = false),
}

enum class ProductionProviderAcceptanceBlocker(val label: String) {
    MissingGateEvidence("missing acceptance-gate evidence"),
    FailedGateEvidence("failed acceptance-gate evidence"),
    UnknownGateEvidence("unknown acceptance-gate evidence"),
    DebugOrTestProviderNotReleaseSelectable("debug or test provider cannot be selectable in release readiness"),
    ProductionProviderSelectionStillDisabled("production provider selection remains disabled"),
    ProductionPersistenceStillDisabled("production vault persistence remains disabled"),
}

enum class ProductionProviderAadBindingField(val label: String) {
    VaultHeaderVersion("vault header/version"),
    ProviderSuiteId("provider suite id"),
    RecordType("record type"),
    RecordId("record id"),
    RecordVersionOrCounter("record version/counter"),
    IntegrityCriticalMetadata("integrity-critical metadata"),
}

enum class ProductionProviderTamperCoverage(val label: String) {
    Header("vault header"),
    Ciphertext("ciphertext"),
    Nonce("nonce"),
    Tag("authentication tag"),
    Aad("associated data"),
    RecordMetadata("record metadata"),
    ProviderSuiteMetadata("provider-suite metadata"),
}

enum class ProductionProviderWeakDeviceFailureMode(val label: String) {
    FailClosedWithUserMessage("fail closed with a clear user-facing message"),
}

data class ProductionProviderArgon2idAcceptancePolicy(
    val version: Argon2idVersion,
    val minimumMemoryMiB: Int,
    val passes: Int,
    val lanes: Int,
    val preferredUnlockMillis: Int,
    val acceptableUnlockMillis: Int,
    val twoSecondsIsFailureCondition: Boolean,
    val weakenToForceSubOneSecondAllowed: Boolean,
    val existingVaultParametersAuthoritative: Boolean,
    val silentParameterDowngradeAllowed: Boolean,
    val weakerDeviceFailureMode: ProductionProviderWeakDeviceFailureMode,
)

data class ProductionProviderAeadAcceptancePolicy(
    val primitive: EncryptedVaultAeadAlgorithm,
    val nonKeyCommitting: Boolean,
    val successfulDecryptAloneProvesCorrectVaultKey: Boolean,
    val vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt: Boolean,
    val headerAuthenticationRequiredBeforeRecordDecrypt: Boolean,
    val strictAadBindingFields: Set<ProductionProviderAadBindingField>,
    val requiredTamperCoverage: Set<ProductionProviderTamperCoverage>,
)

data class ProductionProviderRuntimeRandomnessAcceptancePolicy(
    val sourceKind: RuntimeRandomnessSourceKind,
    val exactProviderPathLabel: String,
    val unknownProviderStateBlocksVaultCreation: Boolean,
    val forbiddenRandomApisRejected: Boolean,
    val tinySampleIsEntropyQualityProof: Boolean,
)

data class ProductionProviderAndroidWrappingAcceptancePolicy(
    val optionalFutureConvenienceLayerOnly: Boolean,
    val hardwareOrBiometricWrappingRequired: Boolean,
    val passphrasePrimaryAuthority: Boolean,
    val passwordOnlyModeFirstClass: Boolean,
    val biometricUnlockReplacesPassphrase: Boolean,
    val roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired: Boolean,
)

data class ProductionProviderAcceptanceGateState(
    val gate: ProductionProviderAcceptanceGate,
    val state: ProductionProviderAcceptanceEvidenceState,
    val safeDetail: String,
) {
    val satisfied: Boolean
        get() = state.satisfiesGate
}

data class UserFacingProductionProviderFailureWarning(
    val title: String,
    val safeMessage: String,
    val blockers: Set<ProductionProviderAcceptanceBlocker>,
)

data class ProductionProviderAcceptanceEvidence(
    val gateStates: Map<ProductionProviderAcceptanceGate, ProductionProviderAcceptanceEvidenceState>,
) {
    fun stateFor(gate: ProductionProviderAcceptanceGate): ProductionProviderAcceptanceEvidenceState =
        gateStates[gate] ?: ProductionProviderAcceptanceEvidenceState.Unknown

    companion object {
        fun currentDesignOnly(): ProductionProviderAcceptanceEvidence =
            ProductionProviderAcceptanceEvidence(
                gateStates = mapOf(
                    ProductionProviderAcceptanceGate.ExactProviderSuitePinned to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.XChaCha20Poly1305PrimitivePinned to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.UnknownRandomnessProviderStateRejected to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.ForbiddenRandomApisGuarded to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.AndroidOptionalWrappingSeparateFromPassphrase to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                    ProductionProviderAcceptanceGate.ReleaseReadinessExcludesDebugTestProviders to
                        ProductionProviderAcceptanceEvidenceState.Satisfied,
                ),
            )

        fun allSatisfiedForReviewOnly(): ProductionProviderAcceptanceEvidence =
            ProductionProviderAcceptanceEvidence(
                gateStates = ProductionProviderAcceptanceGate.entries.associateWith {
                    ProductionProviderAcceptanceEvidenceState.Satisfied
                },
            )
    }
}

data class ProductionProviderAcceptanceAssessment(
    val contract: ProductionProviderAcceptanceContract,
    val gateStates: List<ProductionProviderAcceptanceGateState>,
    val blockers: Set<ProductionProviderAcceptanceBlocker>,
    val userFacingWarning: UserFacingProductionProviderFailureWarning?,
    val providerImplementationState: VaultCryptoProviderImplementationState,
) {
    val allRequiredGatesSatisfied: Boolean
        get() = gateStates.all { it.satisfied }

    val productionProviderSelectable: Boolean = false

    val productionPersistenceAllowed: Boolean = false
}

data class ProductionProviderAcceptanceContract(
    val suite: ProductionProviderSuiteIdentity,
    val requiredGates: Set<ProductionProviderAcceptanceGate>,
    val argon2idPolicy: ProductionProviderArgon2idAcceptancePolicy,
    val aeadPolicy: ProductionProviderAeadAcceptancePolicy,
    val runtimeRandomnessPolicy: ProductionProviderRuntimeRandomnessAcceptancePolicy,
    val androidWrappingPolicy: ProductionProviderAndroidWrappingAcceptancePolicy,
) {
    fun assess(
        evidence: ProductionProviderAcceptanceEvidence = ProductionProviderAcceptanceEvidence.currentDesignOnly(),
        providerImplementationState: VaultCryptoProviderImplementationState =
            VaultCryptoProviderImplementationState.ExecutableUnavailable,
        releaseReadiness: Boolean = true,
    ): ProductionProviderAcceptanceAssessment {
        val gateStates = requiredGates.map { gate ->
            val state = evidence.stateFor(gate)
            ProductionProviderAcceptanceGateState(
                gate = gate,
                state = state,
                safeDetail = when (state) {
                    ProductionProviderAcceptanceEvidenceState.Satisfied -> "Gate evidence is recorded."
                    ProductionProviderAcceptanceEvidenceState.Missing -> "Gate evidence is missing."
                    ProductionProviderAcceptanceEvidenceState.Failed -> "Gate evidence failed."
                    ProductionProviderAcceptanceEvidenceState.Unknown -> "Gate evidence is unknown."
                },
            )
        }
        val blockers = buildSet {
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Missing }) {
                add(ProductionProviderAcceptanceBlocker.MissingGateEvidence)
            }
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Failed }) {
                add(ProductionProviderAcceptanceBlocker.FailedGateEvidence)
            }
            if (gateStates.any { it.state == ProductionProviderAcceptanceEvidenceState.Unknown }) {
                add(ProductionProviderAcceptanceBlocker.UnknownGateEvidence)
            }
            if (releaseReadiness && providerImplementationState.testOnly) {
                add(ProductionProviderAcceptanceBlocker.DebugOrTestProviderNotReleaseSelectable)
            }
            add(ProductionProviderAcceptanceBlocker.ProductionProviderSelectionStillDisabled)
            add(ProductionProviderAcceptanceBlocker.ProductionPersistenceStillDisabled)
        }
        return ProductionProviderAcceptanceAssessment(
            contract = this,
            gateStates = gateStates,
            blockers = blockers,
            userFacingWarning = warning(blockers),
            providerImplementationState = providerImplementationState,
        )
    }

    private fun warning(
        blockers: Set<ProductionProviderAcceptanceBlocker>,
    ): UserFacingProductionProviderFailureWarning? =
        if (blockers.isEmpty()) {
            null
        } else {
            UserFacingProductionProviderFailureWarning(
                title = "Vault provider unavailable",
                safeMessage = "The encrypted vault provider is not approved for production use; vault creation and persistence must remain disabled.",
                blockers = blockers,
            )
        }

    companion object {
        fun v1(): ProductionProviderAcceptanceContract =
            ProductionProviderAcceptanceContract(
                suite = ProductionProviderSuiteIdentity(
                    suiteId = "skald-vault-v1-bouncycastle-argon2id-tink-xchacha20poly1305-os-securerandom",
                    model = ProductionProviderSuiteModel.SinglePinnedSuite,
                    kdf = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.Kdf,
                        implementation = "Bouncy Castle",
                        algorithm = "Argon2id",
                        versionOrTemplate = "Argon2id version 19",
                        artifact = "org.bouncycastle:bcprov-jdk18on:1.84",
                    ),
                    aead = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.Aead,
                        implementation = "Tink",
                        algorithm = "XChaCha20-Poly1305",
                        versionOrTemplate = "Tink XChaCha20-Poly1305 key/template pinned for v1",
                        artifact = "com.google.crypto.tink:tink/tink-android:1.21.0",
                    ),
                    runtimeRandomness = ProductionProviderPrimitiveIdentity(
                        role = ProductionProviderPrimitiveRole.RuntimeRandomness,
                        implementation = "OS SecureRandom",
                        algorithm = "OS cryptographic randomness",
                        versionOrTemplate = "provider and algorithm evidence required at runtime",
                        artifact = null,
                    ),
                    pinnedArtifacts = setOf(
                        "org.bouncycastle:bcprov-jdk18on:1.84",
                        "com.google.crypto.tink:tink:1.21.0",
                        "com.google.crypto.tink:tink-android:1.21.0",
                    ),
                ),
                requiredGates = ProductionProviderAcceptanceGate.entries.toSet(),
                argon2idPolicy = ProductionProviderArgon2idAcceptancePolicy(
                    version = Argon2idVersion.Version19,
                    minimumMemoryMiB = 64,
                    passes = 3,
                    lanes = 1,
                    preferredUnlockMillis = 1_000,
                    acceptableUnlockMillis = 2_000,
                    twoSecondsIsFailureCondition = false,
                    weakenToForceSubOneSecondAllowed = false,
                    existingVaultParametersAuthoritative = true,
                    silentParameterDowngradeAllowed = false,
                    weakerDeviceFailureMode = ProductionProviderWeakDeviceFailureMode.FailClosedWithUserMessage,
                ),
                aeadPolicy = ProductionProviderAeadAcceptancePolicy(
                    primitive = EncryptedVaultAeadAlgorithm.XChaCha20Poly1305,
                    nonKeyCommitting = true,
                    successfulDecryptAloneProvesCorrectVaultKey = false,
                    vaultLevelKeyCommitmentRequiredBeforeRecordDecrypt = true,
                    headerAuthenticationRequiredBeforeRecordDecrypt = true,
                    strictAadBindingFields = ProductionProviderAadBindingField.entries.toSet(),
                    requiredTamperCoverage = ProductionProviderTamperCoverage.entries.toSet(),
                ),
                runtimeRandomnessPolicy = ProductionProviderRuntimeRandomnessAcceptancePolicy(
                    sourceKind = RuntimeRandomnessSourceKind.OsCryptographicRandomness,
                    exactProviderPathLabel = "OS SecureRandom",
                    unknownProviderStateBlocksVaultCreation = true,
                    forbiddenRandomApisRejected = true,
                    tinySampleIsEntropyQualityProof = false,
                ),
                androidWrappingPolicy = ProductionProviderAndroidWrappingAcceptancePolicy(
                    optionalFutureConvenienceLayerOnly = true,
                    hardwareOrBiometricWrappingRequired = false,
                    passphrasePrimaryAuthority = true,
                    passwordOnlyModeFirstClass = true,
                    biometricUnlockReplacesPassphrase = false,
                    roughlyWeeklyPassphrasePromptAfterBiometricUnlockRequired = true,
                ),
            )
    }
}

fun commonProductionProviderAcceptanceContract(): ProductionProviderAcceptanceContract =
    ProductionProviderAcceptanceContract.v1()

fun commonCurrentProductionProviderAcceptanceAssessment(): ProductionProviderAcceptanceAssessment =
    commonProductionProviderAcceptanceContract().assess()
