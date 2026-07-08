package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultStorageReadinessDecisionSafeLabel(val value: String) {
    override fun toString(): String = "RedactedEncryptedVaultStorageReadinessDecisionSafeLabel"
}

enum class EncryptedVaultStorageReadinessDecisionKind(val label: String) {
    EncryptedLocalVaultStorageReadinessDecision("ENCRYPTED_LOCAL_VAULT_STORAGE_READINESS_DECISION"),
}

enum class EncryptedVaultStorageReadinessDecisionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultStorageReadinessDecisionCheck {
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    DesktopDependencyKatPassed,
    AndroidDependencyKatPassed,
    Argon2idCalibrationProbePresent,
    DisabledVaultCryptoProviderBoundaryPresent,
    TestOnlyProviderImplementationPresent,
    ProviderLevelPublicKatPassed,
    TestOnlyProviderSelectionValidationPassed,
    ProviderSelectionValidationCompletionAuditPassed,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    ProductionSyncFacadePresent,
    RecoveryPrivacyPersistenceBlockersPresent,
    BackendObservationStateBoundaryPresent,
    ProductionBackendAdapterBoundaryPresent,
    ReceiveAddressPolicyBoundaryPresent,
    EncryptedVaultStorageImplementationPathAdmitted,
    VaultContainerFormatDecisionAdmitted,
    StoragePathPolicyDecisionAdmitted,
    LockSessionLifecycleDecisionAdmitted,
    MigrationCorruptionPolicyDecisionAdmitted,
    BackupExportPolicyDecisionAdmitted,
    FutureVaultContainerImplementationRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    VaultContainerImplementationAbsent,
    EncryptedVaultFileFormatNotImplemented,
    EncryptedVaultRepositorySuccessAbsent,
    SecureSecretStorageSuccessPathAbsent,
    SecureMetadataStorageSuccessPathAbsent,
    ProductionObservationPersistenceAbsent,
    ProductionAddressIndexPersistenceAbsent,
    ProductionUtxoPersistenceAbsent,
    ProductionWalletHistoryPersistenceAbsent,
    ProductionSyncAbsent,
    ProductionBackendClientAbsent,
    ProviderChoiceNotPersisted,
    ProviderSelectionUiAbsent,
    ProductionProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionSelectionStillDisabledProviderOnly,
    SigningBroadcastingAbsent,
    UiActionEnablementAbsent,
    EndpointAbsent,
    MainnetAbsent,
    ProductionStorageAuthorizationAbsent,
    ProductionSecretStorageAuthorizationAbsent,
    ProductionMetadataStorageAuthorizationAbsent,
    ProductionSyncAuthorizationAbsent,
    ProductionProviderSelectionAuthorizationAbsent,
    ProductionProviderImplementationAuthorizationAbsent,
    SigningBroadcastingAuthorizationAbsent,
    UiAuthorizationAbsent,
    EndpointAuthorizationAbsent,
    MainnetAuthorizationAbsent,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultStorageReadinessDecisionFailureLabel {
    ExistingEvidenceMissing,
    LaterStorageAdmissionMissing,
    FutureSeparatePassGateMissing,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    ProductionProviderImplementationSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultStorageReadinessDecision(
    val decisionId: EncryptedVaultStorageReadinessDecisionSafeLabel,
    val decisionVersion: Int,
    val decisionKind: EncryptedVaultStorageReadinessDecisionKind,
    val sourceSet: EncryptedVaultStorageReadinessDecisionSourceSet,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val desktopDependencyKatPassed: Boolean,
    val androidDependencyKatPassed: Boolean,
    val argon2idCalibrationProbePresent: Boolean,
    val disabledVaultCryptoProviderBoundaryPresent: Boolean,
    val testOnlyProviderImplementationPresent: Boolean,
    val providerLevelPublicKatPassed: Boolean,
    val testOnlyProviderSelectionValidationPassed: Boolean,
    val providerSelectionValidationCompletionAuditPassed: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val productionSyncFacadePresent: Boolean,
    val recoveryPrivacyPersistenceBlockersPresent: Boolean,
    val backendObservationStateBoundaryPresent: Boolean,
    val productionBackendAdapterBoundaryPresent: Boolean,
    val receiveAddressPolicyBoundaryPresent: Boolean,
    val encryptedVaultStorageImplementationPathAdmitted: Boolean,
    val vaultContainerFormatDecisionAdmitted: Boolean,
    val storagePathPolicyDecisionAdmitted: Boolean,
    val lockSessionLifecycleDecisionAdmitted: Boolean,
    val migrationCorruptionPolicyDecisionAdmitted: Boolean,
    val backupExportPolicyDecisionAdmitted: Boolean,
    val futureVaultContainerImplementationRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val vaultContainerImplementationPresent: Boolean,
    val encryptedVaultFileFormatImplemented: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val providerChoicePersisted: Boolean,
    val providerSelectionUiPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val productionStorageAuthorizationPresent: Boolean,
    val productionSecretStorageAuthorizationPresent: Boolean,
    val productionMetadataStorageAuthorizationPresent: Boolean,
    val productionSyncAuthorizationPresent: Boolean,
    val productionProviderSelectionAuthorizationPresent: Boolean,
    val productionProviderImplementationAuthorizationPresent: Boolean,
    val signingBroadcastingAuthorizationPresent: Boolean,
    val uiAuthorizationPresent: Boolean,
    val endpointAuthorizationPresent: Boolean,
    val mainnetAuthorizationPresent: Boolean,
    val encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly: Boolean,
    val vaultContainerFormatDecisionAdmittedIsNotVaultImplementation: Boolean,
    val storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization: Boolean,
    val lockSessionLifecycleDecisionAdmittedIsNotUnlockImplementation: Boolean,
    val migrationCorruptionPolicyDecisionAdmittedIsNotMigrationExecution: Boolean,
    val backupExportPolicyDecisionAdmittedIsNotBackupExportImplementation: Boolean,
    val providerLevelKatSuccessIsTestSourceOnlyEvidence: Boolean,
    val testOnlyProviderSelectionValidationIsTestSourceOnlyEvidence: Boolean,
    val selectedProviderKatSuccessIsNotVaultPersistenceAuthorization: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val storageReadinessDecisionPassed: Boolean,
    val evidenceCount: Int,
    val readinessCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val readinessChecks: List<EncryptedVaultStorageReadinessDecisionCheck>,
    val failureLabels: List<EncryptedVaultStorageReadinessDecisionFailureLabel>,
    val displayLabel: EncryptedVaultStorageReadinessDecisionSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultStorageReadinessDecision(" +
            "REDACTED, COMMON_MAIN_POLICY, STORAGE_READINESS_DECISION_ONLY, " +
            "LATER_BRANCH_ONLY, NO_STORAGE_IMPLEMENTATION, NO_VAULT_CONTAINER_IO, " +
            "NO_SECURE_STORAGE_SUCCESS, NO_PRODUCTION_SYNC, DISABLED_PROVIDER_ONLY, " +
            "NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultStorageReadinessDecisionPolicy {
    fun currentStorageReadinessDecision(): EncryptedVaultStorageReadinessDecision {
        val readiness = commonDisabledEncryptedVaultReadiness()
        val readinessDecision = EncryptedVaultReadinessPolicy.evaluate(readiness)
        val secureStorageCapability = commonDisabledSecureStorageCapability()
        val secureMetadataCapability = commonDisabledSecureMetadataCapability()
        val providerStatus = commonDisabledVaultCryptoProviderStatus()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val argon2idPolicy = commonArgon2idCalibrationPolicy()
        val dependencyResult = VaultCryptoDependencyProbeCatalog.currentSpikeResults()
            .single { result -> result.candidate == VaultCryptoDependencyCandidate.TinkBouncyCastleSplit }
        val dependencyCapabilities = dependencyResult.capabilities
        val checks = EncryptedVaultStorageReadinessDecisionCheck.entries.toList()

        val encryptedVaultDesignPresent =
            readiness.implementationStatus == EncryptedVaultImplementationStatus.NotImplemented &&
                readiness.algorithmPolicy.designOnly
        val cryptoDecisionPresent =
            dependencyCapabilities.contains(VaultCryptoDependencyCapability.Argon2idApiPresent) &&
                dependencyCapabilities.contains(VaultCryptoDependencyCapability.XChaCha20Poly1305ApiPresent)
        val dependencyReviewPresent =
            dependencyResult.status ==
                VaultCryptoDependencyProbeStatus.DependencyLicenseAndKeysetReviewCompleteCandidate &&
                dependencyCapabilities.contains(VaultCryptoDependencyCapability.DependencyInventoryReviewed) &&
                dependencyCapabilities.contains(VaultCryptoDependencyCapability.LicenseDeclarationsInspected) &&
                dependencyCapabilities.contains(VaultCryptoDependencyCapability.TinkKeysetStorageReviewDocumented)
        val desktopDependencyKatPassed =
            dependencyCapabilities.contains(VaultCryptoDependencyCapability.DesktopKnownAnswerVectorsPass)
        val androidDependencyKatPassed =
            dependencyCapabilities.contains(VaultCryptoDependencyCapability.AndroidKnownAnswerVectorsPass)
        val argon2idCalibrationProbePresent =
            argon2idPolicy.status ==
                Argon2idCalibrationImplementationStatus.StillDisabledBuildingBlockImplemented &&
                !argon2idPolicy.productionKdfEnabled
        val disabledVaultCryptoProviderBoundaryPresent =
            providerStatus.implementationStatus == VaultCryptoProviderImplementationStatus.DisabledBoundaryOnly &&
                !providerStatus.canDeriveKeys &&
                !providerStatus.canEncryptRecords &&
                !providerStatus.productionPersistenceEnabled &&
                !providerStatus.mainnetEnabled
        val secureStorageBoundaryPresent =
            secureStorageCapability.isFailClosed && !secureStorageCapability.status.availableForSecretMaterial
        val secureMetadataBoundaryPresent =
            secureMetadataCapability.isFailClosed &&
                !secureMetadataCapability.status.availableForSensitiveMetadata
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable

        val testOnlyProviderImplementationPresent = true
        val providerLevelPublicKatPassed = true
        val testOnlyProviderSelectionValidationPassed = true
        val providerSelectionValidationCompletionAuditPassed = true
        val productionSyncFacadePresent = true
        val recoveryPrivacyPersistenceBlockersPresent = true
        val backendObservationStateBoundaryPresent = true
        val productionBackendAdapterBoundaryPresent = true
        val receiveAddressPolicyBoundaryPresent = true

        val encryptedVaultStorageImplementationPathAdmitted = true
        val vaultContainerFormatDecisionAdmitted = true
        val storagePathPolicyDecisionAdmitted = true
        val lockSessionLifecycleDecisionAdmitted = true
        val migrationCorruptionPolicyDecisionAdmitted = true
        val backupExportPolicyDecisionAdmitted = true
        val futureVaultContainerImplementationRequiresSeparatePass = true
        val futureSecureStorageSuccessRequiresSeparatePass = true
        val futureSecureMetadataSuccessRequiresSeparatePass = true
        val futureProductionSyncRequiresSeparatePass = true
        val futureProductionProviderSelectionRequiresSeparatePass = true

        val vaultContainerImplementationPresent = false
        val encryptedVaultFileFormatImplemented = false
        val encryptedVaultRepositorySuccessPresent = false
        val secureSecretStorageSuccessPathPresent =
            secureStorageCapability.status.availableForSecretMaterial &&
                secureStorageCapability.canStoreSecrets &&
                secureStorageCapability.canReadSecrets
        val secureMetadataStorageSuccessPathPresent =
            secureMetadataCapability.status.availableForSensitiveMetadata &&
                secureMetadataCapability.canStoreMetadata &&
                secureMetadataCapability.canReadMetadata
        val productionObservationPersistencePresent = false
        val productionAddressIndexPersistencePresent = false
        val productionUtxoPersistencePresent = false
        val productionWalletHistoryPersistencePresent = false
        val productionSyncPresent = false
        val productionBackendClientPresent = false
        val providerChoicePersisted = false
        val providerSelectionUiPresent = false
        val signingBroadcastingPresent = false
        val uiActionEnablementPresent = false
        val endpointPresent = false
        val mainnetPresent = false

        val productionStorageAuthorizationPresent = false
        val productionSecretStorageAuthorizationPresent = false
        val productionMetadataStorageAuthorizationPresent = false
        val productionSyncAuthorizationPresent = false
        val productionProviderSelectionAuthorizationPresent = false
        val productionProviderImplementationAuthorizationPresent = false
        val signingBroadcastingAuthorizationPresent = false
        val uiAuthorizationPresent = false
        val endpointAuthorizationPresent = false
        val mainnetAuthorizationPresent = false

        val encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly =
            encryptedVaultStorageImplementationPathAdmitted &&
                futureVaultContainerImplementationRequiresSeparatePass &&
                !vaultContainerImplementationPresent &&
                !encryptedVaultRepositorySuccessPresent
        val vaultContainerFormatDecisionAdmittedIsNotVaultImplementation =
            vaultContainerFormatDecisionAdmitted &&
                !vaultContainerImplementationPresent &&
                !encryptedVaultFileFormatImplemented
        val storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization =
            storagePathPolicyDecisionAdmitted &&
                !encryptedVaultRepositorySuccessPresent &&
                !productionStorageAuthorizationPresent
        val lockSessionLifecycleDecisionAdmittedIsNotUnlockImplementation =
            lockSessionLifecycleDecisionAdmitted &&
                !productionStorageAuthorizationPresent &&
                !productionSecretStorageAuthorizationPresent
        val migrationCorruptionPolicyDecisionAdmittedIsNotMigrationExecution =
            migrationCorruptionPolicyDecisionAdmitted &&
                !encryptedVaultFileFormatImplemented &&
                !encryptedVaultRepositorySuccessPresent
        val backupExportPolicyDecisionAdmittedIsNotBackupExportImplementation =
            backupExportPolicyDecisionAdmitted &&
                !productionSecretStorageAuthorizationPresent &&
                !productionMetadataStorageAuthorizationPresent
        val providerLevelKatSuccessIsTestSourceOnlyEvidence =
            providerLevelPublicKatPassed &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val testOnlyProviderSelectionValidationIsTestSourceOnlyEvidence =
            testOnlyProviderSelectionValidationPassed &&
                providerSelectionValidationCompletionAuditPassed &&
                productionSelectionStillDisabledProviderOnly
        val selectedProviderKatSuccessIsNotVaultPersistenceAuthorization =
            !encryptedVaultRepositorySuccessPresent &&
                !secureSecretStorageSuccessPathPresent &&
                !secureMetadataStorageSuccessPathPresent &&
                !productionStorageAuthorizationPresent
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val existingEvidencePresent =
            encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                desktopDependencyKatPassed &&
                androidDependencyKatPassed &&
                argon2idCalibrationProbePresent &&
                disabledVaultCryptoProviderBoundaryPresent &&
                testOnlyProviderImplementationPresent &&
                providerLevelPublicKatPassed &&
                testOnlyProviderSelectionValidationPassed &&
                providerSelectionValidationCompletionAuditPassed &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent &&
                productionSyncFacadePresent &&
                recoveryPrivacyPersistenceBlockersPresent &&
                backendObservationStateBoundaryPresent &&
                productionBackendAdapterBoundaryPresent &&
                receiveAddressPolicyBoundaryPresent
        val laterStorageAdmissionPresent =
            encryptedVaultStorageImplementationPathAdmitted &&
                vaultContainerFormatDecisionAdmitted &&
                storagePathPolicyDecisionAdmitted &&
                lockSessionLifecycleDecisionAdmitted &&
                migrationCorruptionPolicyDecisionAdmitted &&
                backupExportPolicyDecisionAdmitted
        val futureSeparatePassGatesPresent =
            futureVaultContainerImplementationRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val productionStorageSurfacePresent =
            vaultContainerImplementationPresent ||
                encryptedVaultFileFormatImplemented ||
                encryptedVaultRepositorySuccessPresent ||
                secureSecretStorageSuccessPathPresent ||
                secureMetadataStorageSuccessPathPresent ||
                productionObservationPersistencePresent ||
                productionAddressIndexPersistencePresent ||
                productionUtxoPersistencePresent ||
                productionWalletHistoryPersistencePresent
        val productionSyncSurfacePresent =
            productionSyncPresent || productionBackendClientPresent
        val productionProviderSelectionSurfacePresent =
            productionProviderSelectionEnabled ||
                productionProviderSelectable ||
                providerChoicePersisted ||
                providerSelectionUiPresent
        val signingBroadcastingUiEndpointOrMainnetSurfacePresent =
            signingBroadcastingPresent ||
                uiActionEnablementPresent ||
                endpointPresent ||
                mainnetPresent
        val productionAuthorizationPresent =
            productionStorageAuthorizationPresent ||
                productionSecretStorageAuthorizationPresent ||
                productionMetadataStorageAuthorizationPresent ||
                productionSyncAuthorizationPresent ||
                productionProviderSelectionAuthorizationPresent ||
                productionProviderImplementationAuthorizationPresent ||
                signingBroadcastingAuthorizationPresent ||
                uiAuthorizationPresent ||
                endpointAuthorizationPresent ||
                mainnetAuthorizationPresent
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!existingEvidencePresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.ExistingEvidenceMissing)
            }
            if (!laterStorageAdmissionPresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.LaterStorageAdmissionMissing)
            }
            if (!futureSeparatePassGatesPresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.FutureSeparatePassGateMissing)
            }
            if (productionStorageSurfacePresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.ProductionStorageSurfacePresent)
            }
            if (productionSyncSurfacePresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.ProductionSyncSurfacePresent)
            }
            if (productionProviderSelectionSurfacePresent) {
                add(
                    EncryptedVaultStorageReadinessDecisionFailureLabel
                        .ProductionProviderSelectionSurfacePresent,
                )
            }
            if (!productionSelectionStillDisabledProviderOnly) {
                add(
                    EncryptedVaultStorageReadinessDecisionFailureLabel
                        .ProductionProviderImplementationSurfacePresent,
                )
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultStorageReadinessDecisionFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.ProductionAuthorizationPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultStorageReadinessDecisionFailureLabel.CorpusBoundaryMissing)
            }
        }

        val storageReadinessDecisionPassed =
            failures.isEmpty() &&
                !readinessDecision.canEnableProductionPersistence &&
                encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly &&
                vaultContainerFormatDecisionAdmittedIsNotVaultImplementation &&
                storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization &&
                selectedProviderKatSuccessIsNotVaultPersistenceAuthorization

        return EncryptedVaultStorageReadinessDecision(
            decisionId = EncryptedVaultStorageReadinessDecisionSafeLabel(
                "skald-encrypted-vault-storage-readiness-decision-v1",
            ),
            decisionVersion = 1,
            decisionKind =
                EncryptedVaultStorageReadinessDecisionKind.EncryptedLocalVaultStorageReadinessDecision,
            sourceSet = EncryptedVaultStorageReadinessDecisionSourceSet.CommonMainPolicy,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            desktopDependencyKatPassed = desktopDependencyKatPassed,
            androidDependencyKatPassed = androidDependencyKatPassed,
            argon2idCalibrationProbePresent = argon2idCalibrationProbePresent,
            disabledVaultCryptoProviderBoundaryPresent = disabledVaultCryptoProviderBoundaryPresent,
            testOnlyProviderImplementationPresent = testOnlyProviderImplementationPresent,
            providerLevelPublicKatPassed = providerLevelPublicKatPassed,
            testOnlyProviderSelectionValidationPassed = testOnlyProviderSelectionValidationPassed,
            providerSelectionValidationCompletionAuditPassed = providerSelectionValidationCompletionAuditPassed,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            productionSyncFacadePresent = productionSyncFacadePresent,
            recoveryPrivacyPersistenceBlockersPresent = recoveryPrivacyPersistenceBlockersPresent,
            backendObservationStateBoundaryPresent = backendObservationStateBoundaryPresent,
            productionBackendAdapterBoundaryPresent = productionBackendAdapterBoundaryPresent,
            receiveAddressPolicyBoundaryPresent = receiveAddressPolicyBoundaryPresent,
            encryptedVaultStorageImplementationPathAdmitted = encryptedVaultStorageImplementationPathAdmitted,
            vaultContainerFormatDecisionAdmitted = vaultContainerFormatDecisionAdmitted,
            storagePathPolicyDecisionAdmitted = storagePathPolicyDecisionAdmitted,
            lockSessionLifecycleDecisionAdmitted = lockSessionLifecycleDecisionAdmitted,
            migrationCorruptionPolicyDecisionAdmitted = migrationCorruptionPolicyDecisionAdmitted,
            backupExportPolicyDecisionAdmitted = backupExportPolicyDecisionAdmitted,
            futureVaultContainerImplementationRequiresSeparatePass =
                futureVaultContainerImplementationRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass = futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass = futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            vaultContainerImplementationPresent = vaultContainerImplementationPresent,
            encryptedVaultFileFormatImplemented = encryptedVaultFileFormatImplemented,
            encryptedVaultRepositorySuccessPresent = encryptedVaultRepositorySuccessPresent,
            secureSecretStorageSuccessPathPresent = secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent = secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent = productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent = productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent = productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent = productionWalletHistoryPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            productionBackendClientPresent = productionBackendClientPresent,
            providerChoicePersisted = providerChoicePersisted,
            providerSelectionUiPresent = providerSelectionUiPresent,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly = productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiActionEnablementPresent = uiActionEnablementPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            productionStorageAuthorizationPresent = productionStorageAuthorizationPresent,
            productionSecretStorageAuthorizationPresent = productionSecretStorageAuthorizationPresent,
            productionMetadataStorageAuthorizationPresent = productionMetadataStorageAuthorizationPresent,
            productionSyncAuthorizationPresent = productionSyncAuthorizationPresent,
            productionProviderSelectionAuthorizationPresent =
                productionProviderSelectionAuthorizationPresent,
            productionProviderImplementationAuthorizationPresent =
                productionProviderImplementationAuthorizationPresent,
            signingBroadcastingAuthorizationPresent = signingBroadcastingAuthorizationPresent,
            uiAuthorizationPresent = uiAuthorizationPresent,
            endpointAuthorizationPresent = endpointAuthorizationPresent,
            mainnetAuthorizationPresent = mainnetAuthorizationPresent,
            encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly =
                encryptedVaultStorageImplementationPathAdmittedIsLaterBranchOnly,
            vaultContainerFormatDecisionAdmittedIsNotVaultImplementation =
                vaultContainerFormatDecisionAdmittedIsNotVaultImplementation,
            storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization =
                storagePathPolicyDecisionAdmittedIsNotStorageWriteAuthorization,
            lockSessionLifecycleDecisionAdmittedIsNotUnlockImplementation =
                lockSessionLifecycleDecisionAdmittedIsNotUnlockImplementation,
            migrationCorruptionPolicyDecisionAdmittedIsNotMigrationExecution =
                migrationCorruptionPolicyDecisionAdmittedIsNotMigrationExecution,
            backupExportPolicyDecisionAdmittedIsNotBackupExportImplementation =
                backupExportPolicyDecisionAdmittedIsNotBackupExportImplementation,
            providerLevelKatSuccessIsTestSourceOnlyEvidence = providerLevelKatSuccessIsTestSourceOnlyEvidence,
            testOnlyProviderSelectionValidationIsTestSourceOnlyEvidence =
                testOnlyProviderSelectionValidationIsTestSourceOnlyEvidence,
            selectedProviderKatSuccessIsNotVaultPersistenceAuthorization =
                selectedProviderKatSuccessIsNotVaultPersistenceAuthorization,
            normalSourceMaterialGuardExcludesBuildHistory = normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            storageReadinessDecisionPassed = storageReadinessDecisionPassed,
            evidenceCount = 18,
            readinessCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            readinessChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultStorageReadinessDecisionSafeLabel(
                "encrypted vault storage readiness decision",
            ),
        )
    }
}
