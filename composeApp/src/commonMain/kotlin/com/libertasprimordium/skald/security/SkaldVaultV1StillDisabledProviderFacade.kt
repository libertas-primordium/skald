package com.libertasprimordium.skald.security

enum class SkaldVaultV1StillDisabledProviderFacadeStatus(
    val label: String,
    val selectable: Boolean,
    val operationsEnabled: Boolean,
) {
    StillDisabled(
        label = "still disabled provider facade",
        selectable = false,
        operationsEnabled = false,
    ),
}

enum class SkaldVaultV1StillDisabledProviderFacadeReason(val label: String) {
    ProviderSelectionDisabled("provider selection is disabled"),
    VaultCreationDisabled("vault creation is disabled"),
    VaultUnlockDisabled("vault unlock is disabled"),
    VaultPersistenceDisabled("vault persistence is disabled"),
    ManifestStorageMissing("manifest and storage integration is missing"),
    SecureSecretStorageDisabled("secure secret storage is disabled"),
    SecureMetadataStorageDisabled("secure metadata storage is disabled"),
    FinalCalibrationApprovalMissing("final Argon2id calibration approval is missing"),
    RuntimeProviderRandomnessReviewMissing("runtime provider and randomness review is missing"),
    ReleaseApprovalMissing("release approval is missing"),
    MainnetDisabled("mainnet remains disabled"),
}

enum class SkaldVaultV1StillDisabledProviderFacadeGate(val label: String) {
    FinalArgon2idCalibrationApproval("final Argon2id calibration approval"),
    RuntimeProviderAndRandomnessReview("runtime provider and randomness review"),
    SelectableProviderImplementationReview("selectable provider implementation review"),
    DesktopAndAndroidProviderKatValidation("desktop and Android provider KAT validation"),
    ManifestBackedStaleRecordPolicy("manifest-backed stale-record policy"),
    VaultContainerStorageReview("vault container and storage review"),
    SecureSecretStorageReview("secure secret storage review"),
    SecureMetadataStorageReview("secure metadata storage review"),
    LockSessionLifecycleReview("lock/session lifecycle review"),
    RedactionAndLeakageReview("redaction and leakage review"),
    MigrationCorruptionReview("migration and corruption review"),
    ReleaseApproval("release approval"),
}

enum class SkaldVaultV1StillDisabledProviderFacadeOperation(val label: String) {
    ReportVaultCreationDisabled("report vault creation disabled"),
    ReportVaultUnlockDisabled("report vault unlock disabled"),
    ReportVaultPersistenceDisabled("report vault persistence disabled"),
    ReportStorageDisabled("report storage disabled"),
    ReportProviderSelectionDisabled("report provider selection disabled"),
}

data class SkaldVaultV1StillDisabledProviderFacadeEvidence(
    val passphrasePolicyImplementedAndTested: Boolean,
    val argon2idRootDerivationImplementedAndTested: Boolean,
    val canonicalHeaderSerializerImplementedAndTested: Boolean,
    val hkdfSha256ImplementedAndTested: Boolean,
    val hmacSha256HeaderCommitmentImplementedAndTested: Boolean,
    val strictAadSerializationImplementedAndTested: Boolean,
    val tinkRecordAeadBuildingBlockImplementedAndTested: Boolean,
    val providerKatHarnessExistsAndExecutes: Boolean,
    val argon2idCalibrationPolicyEvidenceExists: Boolean,
    val productionProviderSelectable: Boolean,
    val vaultCreationEnabled: Boolean,
    val vaultUnlockEnabled: Boolean,
    val vaultPersistenceEnabled: Boolean,
    val manifestStorageImplemented: Boolean,
    val secureStorageEnabled: Boolean,
    val releaseApproved: Boolean,
)

data class SkaldVaultV1StillDisabledProviderFacadeMetadata(
    val suiteId: String,
    val displayName: String,
    val status: SkaldVaultV1StillDisabledProviderFacadeStatus,
    val kdf: String,
    val recordAead: String,
    val runtimeRandomness: String,
    val passphrasePolicyId: String,
    val keyExpansionPolicyId: String,
    val keySeparationPolicyId: String,
    val headerCommitmentPrimitivePolicyId: String,
    val headerCommitmentPolicyId: String,
    val strictAadPolicyId: String,
    val recordFormatPolicyId: String,
    val providerLevelKatPolicyId: String,
    val randomizedAeadBehavioralKatPolicyId: String,
    val verificationOrderKatPolicyId: String,
    val staleRecordManifestPolicyId: String,
    val calibrationPolicyId: String,
    val disabledReasons: Set<SkaldVaultV1StillDisabledProviderFacadeReason>,
    val remainingGates: Set<SkaldVaultV1StillDisabledProviderFacadeGate>,
    val evidence: SkaldVaultV1StillDisabledProviderFacadeEvidence,
)

sealed class SkaldVaultV1StillDisabledProviderFacadeOperationResult {
    data class Disabled(
        val operation: SkaldVaultV1StillDisabledProviderFacadeOperation,
        val reason: SkaldVaultV1StillDisabledProviderFacadeReason,
        val productionProviderSelectable: Boolean,
        val vaultPersistenceEnabled: Boolean,
        val safeMessage: String,
    ) : SkaldVaultV1StillDisabledProviderFacadeOperationResult()
}

object SkaldVaultV1StillDisabledProviderFacade {
    fun metadata(
        contract: ProductionProviderAcceptanceContract = commonProductionProviderAcceptanceContract(),
    ): SkaldVaultV1StillDisabledProviderFacadeMetadata =
        SkaldVaultV1StillDisabledProviderFacadeMetadata(
            suiteId = contract.suite.suiteId,
            displayName = "Skald Vault v1 still-disabled provider facade",
            status = SkaldVaultV1StillDisabledProviderFacadeStatus.StillDisabled,
            kdf = "${contract.suite.kdf.implementation} ${contract.suite.kdf.algorithm}",
            recordAead = "${contract.suite.aead.implementation} ${contract.suite.aead.algorithm}",
            runtimeRandomness = contract.suite.runtimeRandomness.implementation,
            passphrasePolicyId = contract.passphraseEncodingPolicy.policyId,
            keyExpansionPolicyId = contract.keyExpansionPrimitivePolicy.policyId,
            keySeparationPolicyId = contract.keySeparationPolicy.policyId,
            headerCommitmentPrimitivePolicyId = contract.headerCommitmentPrimitivePolicy.policyId,
            headerCommitmentPolicyId = contract.headerCommitmentPolicy.policyId,
            strictAadPolicyId = contract.aeadPolicy.aadPolicyId,
            recordFormatPolicyId = contract.aeadPolicy.recordFormatPolicyId,
            providerLevelKatPolicyId = contract.providerLevelKatStrategyPolicy.policyId,
            randomizedAeadBehavioralKatPolicyId = contract.randomizedAeadBehavioralKatPolicy.policyId,
            verificationOrderKatPolicyId = contract.integratedVerificationOrderKatPolicy.policyId,
            staleRecordManifestPolicyId = contract.staleRecordManifestPolicy.policyId,
            calibrationPolicyId = SkaldVaultV1Argon2idCalibrationPolicy.POLICY_ID,
            disabledReasons = SkaldVaultV1StillDisabledProviderFacadeReason.entries.toSet(),
            remainingGates = SkaldVaultV1StillDisabledProviderFacadeGate.entries.toSet(),
            evidence = evidence(contract),
        )

    fun vaultCreationDisabled(): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        disabled(
            operation = SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultCreationDisabled,
            reason = SkaldVaultV1StillDisabledProviderFacadeReason.VaultCreationDisabled,
        )

    fun vaultUnlockDisabled(): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        disabled(
            operation = SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultUnlockDisabled,
            reason = SkaldVaultV1StillDisabledProviderFacadeReason.VaultUnlockDisabled,
        )

    fun vaultPersistenceDisabled(): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        disabled(
            operation = SkaldVaultV1StillDisabledProviderFacadeOperation.ReportVaultPersistenceDisabled,
            reason = SkaldVaultV1StillDisabledProviderFacadeReason.VaultPersistenceDisabled,
        )

    fun storageDisabled(): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        disabled(
            operation = SkaldVaultV1StillDisabledProviderFacadeOperation.ReportStorageDisabled,
            reason = SkaldVaultV1StillDisabledProviderFacadeReason.ManifestStorageMissing,
        )

    fun providerSelectionDisabled(): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        disabled(
            operation = SkaldVaultV1StillDisabledProviderFacadeOperation.ReportProviderSelectionDisabled,
            reason = SkaldVaultV1StillDisabledProviderFacadeReason.ProviderSelectionDisabled,
        )

    private fun evidence(
        contract: ProductionProviderAcceptanceContract,
    ): SkaldVaultV1StillDisabledProviderFacadeEvidence =
        SkaldVaultV1StillDisabledProviderFacadeEvidence(
            passphrasePolicyImplementedAndTested =
                contract.passphraseEncodingPolicy.productionValidationImplemented,
            argon2idRootDerivationImplementedAndTested =
                contract.argon2idRootDerivationPolicy.productionRootDerivationImplemented,
            canonicalHeaderSerializerImplementedAndTested =
                contract.canonicalHeaderVectorContract.productionSerializerImplemented,
            hkdfSha256ImplementedAndTested =
                contract.hkdfVectorContract.productionHkdfExecutionImplemented,
            hmacSha256HeaderCommitmentImplementedAndTested =
                contract.hmacHeaderCommitmentVectorContract.productionHeaderCommitmentExecutionImplemented,
            strictAadSerializationImplementedAndTested =
                contract.aeadPolicy.strictAadSerializationImplemented,
            tinkRecordAeadBuildingBlockImplementedAndTested =
                contract.aeadPolicy.recordAeadBuildingBlockImplemented,
            providerKatHarnessExistsAndExecutes =
                contract.providerLevelKatStrategyPolicy.stillDisabledProviderLevelKatExecutionImplemented &&
                    contract.integratedVerificationOrderKatPolicy
                        .stillDisabledVerificationOrderKatExecutionImplemented,
            argon2idCalibrationPolicyEvidenceExists =
                SkaldVaultV1Argon2idCalibrationPolicy.evidence
                    .implementationStatus.stillDisabledBuildingBlockImplemented,
            productionProviderSelectable = false,
            vaultCreationEnabled = false,
            vaultUnlockEnabled = false,
            vaultPersistenceEnabled = false,
            manifestStorageImplemented = false,
            secureStorageEnabled = false,
            releaseApproved = false,
        )

    private fun disabled(
        operation: SkaldVaultV1StillDisabledProviderFacadeOperation,
        reason: SkaldVaultV1StillDisabledProviderFacadeReason,
    ): SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled =
        SkaldVaultV1StillDisabledProviderFacadeOperationResult.Disabled(
            operation = operation,
            reason = reason,
            productionProviderSelectable = false,
            vaultPersistenceEnabled = false,
            safeMessage = "Skald Vault v1 provider facade is still disabled; no vault operation was performed.",
        )
}
