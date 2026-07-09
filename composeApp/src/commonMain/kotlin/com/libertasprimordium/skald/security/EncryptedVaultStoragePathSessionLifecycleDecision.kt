package com.libertasprimordium.skald.security

@JvmInline
value class EncryptedVaultStoragePathSessionLifecycleSafeLabel(val value: String) {
    override fun toString(): String = "RedactedEncryptedVaultStoragePathSessionLifecycleSafeLabel"
}

enum class EncryptedVaultStoragePathSessionLifecycleDecisionKind(val label: String) {
    EncryptedLocalVaultStoragePathSessionLifecycleDecision(
        "ENCRYPTED_LOCAL_VAULT_STORAGE_PATH_SESSION_LIFECYCLE_DECISION",
    ),
}

enum class EncryptedVaultStoragePathSessionLifecycleDecisionSourceSet(val label: String) {
    CommonMainPolicy("COMMON_MAIN_POLICY"),
}

enum class EncryptedVaultStoragePathSessionLifecyclePolicyLabel(
    val safeLabel: EncryptedVaultStoragePathSessionLifecycleSafeLabel,
) {
    StoragePathPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-encrypted-local-vault-storage-path-policy-v1",
        ),
    ),
    SessionLifecyclePolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-encrypted-local-vault-session-lifecycle-policy-v1",
        ),
    ),
    AndroidAppPrivateStoragePolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-android-app-private-storage-policy",
        ),
    ),
    LinuxUserDataStoragePolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-linux-user-data-storage-policy",
        ),
    ),
    NoPlaintextCachePolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-no-plaintext-cache-policy",
        ),
    ),
    LockOnBackgroundPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-lock-on-background-policy",
        ),
    ),
    LockOnProcessDeathPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-lock-on-process-death-policy",
        ),
    ),
    ExplicitUnlockRequiredPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-explicit-unlock-required-policy",
        ),
    ),
    AppControlledVaultDirectoryPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-app-controlled-vault-directory-policy",
        ),
    ),
    BackupExportStorageSeparationPolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-backup-export-storage-separation-policy",
        ),
    ),
    PlatformWrappingReferencePolicy(
        EncryptedVaultStoragePathSessionLifecycleSafeLabel(
            "skald-vault-v1-platform-wrapping-reference-policy",
        ),
    ),
}

enum class EncryptedVaultStoragePathSessionLifecycleDecisionCheck {
    StorageReadinessDecisionPresent,
    ContainerFormatV1DecisionPresent,
    EncryptedVaultDesignPresent,
    CryptoDecisionPresent,
    DependencyReviewPresent,
    ProviderBoundaryPresent,
    ProviderSelectionValidationCompletionAuditPresent,
    SecureStorageBoundaryPresent,
    SecureMetadataBoundaryPresent,
    VaultStoragePathSessionLifecycleDecisionAdmitted,
    AndroidAppPrivateStoragePolicyAdmitted,
    LinuxUserDataStoragePolicyAdmitted,
    AppControlledVaultDirectoryPolicyAdmitted,
    NoPlaintextCachePolicyAdmitted,
    NoNonSecretSettingsStorageForVaultPolicyAdmitted,
    BackupExportStorageSeparationPolicyAdmitted,
    ExplicitUnlockRequiredPolicyAdmitted,
    LockOnAppBackgroundPolicyAdmitted,
    LockOnProcessDeathPolicyAdmitted,
    LockOnExplicitUserActionPolicyAdmitted,
    SessionKeyMemoryHandlingPolicyAdmitted,
    PlatformWrappingPolicyReferenceAdmitted,
    FutureStoragePathImplementationRequiresSeparatePass,
    FutureLockSessionImplementationRequiresSeparatePass,
    FutureVaultContainerParserRequiresSeparatePass,
    FutureVaultContainerWriterRequiresSeparatePass,
    FutureVaultStorageRepositoryRequiresSeparatePass,
    FutureSecureStorageSuccessRequiresSeparatePass,
    FutureSecureMetadataSuccessRequiresSeparatePass,
    FutureProductionSyncRequiresSeparatePass,
    FutureProductionProviderSelectionRequiresSeparatePass,
    VaultStoragePathImplementationAbsent,
    VaultDirectoryNotCreated,
    VaultFileReadAbsent,
    VaultFileWriteAbsent,
    VaultFileDeleteAbsent,
    VaultContainerParserAbsent,
    VaultContainerWriterAbsent,
    VaultContainerSerializationAbsent,
    VaultContainerParsingAbsent,
    VaultContainerBytesNotProduced,
    EncryptedVaultFileFormatNotImplemented,
    EncryptedVaultRepositorySuccessAbsent,
    LockSessionImplementationAbsent,
    UnlockImplementationAbsent,
    RuntimeSessionKeyAbsent,
    SessionKeyNotCached,
    PlaintextCacheAbsent,
    NonSecretSettingsVaultStorageAbsent,
    SharedPreferencesVaultStorageAbsent,
    DesktopConfigVaultStorageAbsent,
    SecureSecretStorageSuccessPathAbsent,
    SecureMetadataStorageSuccessPathAbsent,
    ProductionObservationPersistenceAbsent,
    ProductionAddressIndexPersistenceAbsent,
    ProductionUtxoPersistenceAbsent,
    ProductionWalletHistoryPersistenceAbsent,
    ProductionSyncAbsent,
    ProductionBackendClientAbsent,
    ProductionProviderSelectionDisabled,
    ProductionProviderNotSelectable,
    ProductionSelectionStillDisabledProviderOnly,
    SigningBroadcastingAbsent,
    UiActionEnablementAbsent,
    EndpointAbsent,
    MainnetAbsent,
    VaultStoragePathImplementationAuthorizationAbsent,
    VaultDirectoryCreationAuthorizationAbsent,
    VaultFileReadAuthorizationAbsent,
    VaultFileWriteAuthorizationAbsent,
    VaultFileDeleteAuthorizationAbsent,
    VaultSessionImplementationAuthorizationAbsent,
    VaultUnlockAuthorizationAbsent,
    VaultLockAuthorizationAbsent,
    VaultContainerImplementationAuthorizationAbsent,
    VaultContainerParserAuthorizationAbsent,
    VaultContainerWriterAuthorizationAbsent,
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
    StoragePathPolicyContainsNoConcreteStorageStrings,
    LockSessionPolicyCreatesNoRuntimeSessionObject,
    LockSessionPolicyStoresNoKeyMaterial,
    PlatformWrappingDoesNotImplementAndroidKeystoreCall,
    PlatformWrappingDoesNotImplementLinuxKeyringCall,
    BuildHistoryExcludedFromNormalSourceMaterialCorpus,
    LocalArtifactRootExcludedFromNormalSourceMaterialCorpus,
}

enum class EncryptedVaultStoragePathSessionLifecycleFailureLabel {
    ExistingEvidenceMissing,
    StoragePathSessionAdmissionMissing,
    FutureSeparatePassGateMissing,
    StoragePathRuntimeSurfacePresent,
    LockSessionRuntimeSurfacePresent,
    ContainerRuntimeSurfacePresent,
    ProductionStorageSurfacePresent,
    ProductionSyncSurfacePresent,
    ProductionProviderSelectionSurfacePresent,
    SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
    ProductionAuthorizationPresent,
    PolicyMaterialPresent,
    CorpusBoundaryMissing,
}

data class EncryptedVaultStoragePathSessionLifecycleDecision(
    val decisionId: EncryptedVaultStoragePathSessionLifecycleSafeLabel,
    val decisionVersion: Int,
    val decisionKind: EncryptedVaultStoragePathSessionLifecycleDecisionKind,
    val sourceSet: EncryptedVaultStoragePathSessionLifecycleDecisionSourceSet,
    val storageReadinessDecisionPresent: Boolean,
    val containerFormatV1DecisionPresent: Boolean,
    val encryptedVaultDesignPresent: Boolean,
    val cryptoDecisionPresent: Boolean,
    val dependencyReviewPresent: Boolean,
    val providerBoundaryPresent: Boolean,
    val providerSelectionValidationCompletionAuditPresent: Boolean,
    val secureStorageBoundaryPresent: Boolean,
    val secureMetadataBoundaryPresent: Boolean,
    val vaultStoragePathSessionLifecycleDecisionAdmitted: Boolean,
    val androidAppPrivateStoragePolicyAdmitted: Boolean,
    val linuxUserDataStoragePolicyAdmitted: Boolean,
    val appControlledVaultDirectoryPolicyAdmitted: Boolean,
    val noPlaintextCachePolicyAdmitted: Boolean,
    val noNonSecretSettingsStorageForVaultPolicyAdmitted: Boolean,
    val backupExportStorageSeparationPolicyAdmitted: Boolean,
    val explicitUnlockRequiredPolicyAdmitted: Boolean,
    val lockOnAppBackgroundPolicyAdmitted: Boolean,
    val lockOnProcessDeathPolicyAdmitted: Boolean,
    val lockOnExplicitUserActionPolicyAdmitted: Boolean,
    val sessionKeyMemoryHandlingPolicyAdmitted: Boolean,
    val platformWrappingPolicyReferenceAdmitted: Boolean,
    val futureStoragePathImplementationRequiresSeparatePass: Boolean,
    val futureLockSessionImplementationRequiresSeparatePass: Boolean,
    val futureVaultContainerParserRequiresSeparatePass: Boolean,
    val futureVaultContainerWriterRequiresSeparatePass: Boolean,
    val futureVaultStorageRepositoryRequiresSeparatePass: Boolean,
    val futureSecureStorageSuccessRequiresSeparatePass: Boolean,
    val futureSecureMetadataSuccessRequiresSeparatePass: Boolean,
    val futureProductionSyncRequiresSeparatePass: Boolean,
    val futureProductionProviderSelectionRequiresSeparatePass: Boolean,
    val vaultStoragePathImplementationPresent: Boolean,
    val vaultDirectoryCreated: Boolean,
    val vaultFileReadPresent: Boolean,
    val vaultFileWritePresent: Boolean,
    val vaultFileDeletePresent: Boolean,
    val vaultContainerParserPresent: Boolean,
    val vaultContainerWriterPresent: Boolean,
    val vaultContainerSerializationPresent: Boolean,
    val vaultContainerParsingPresent: Boolean,
    val vaultContainerBytesProduced: Boolean,
    val encryptedVaultFileFormatImplemented: Boolean,
    val encryptedVaultRepositorySuccessPresent: Boolean,
    val lockSessionImplementationPresent: Boolean,
    val unlockImplementationPresent: Boolean,
    val runtimeSessionKeyPresent: Boolean,
    val sessionKeyCached: Boolean,
    val plaintextCachePresent: Boolean,
    val nonSecretSettingsVaultStoragePresent: Boolean,
    val sharedPreferencesVaultStoragePresent: Boolean,
    val desktopConfigVaultStoragePresent: Boolean,
    val secureSecretStorageSuccessPathPresent: Boolean,
    val secureMetadataStorageSuccessPathPresent: Boolean,
    val productionObservationPersistencePresent: Boolean,
    val productionAddressIndexPersistencePresent: Boolean,
    val productionUtxoPersistencePresent: Boolean,
    val productionWalletHistoryPersistencePresent: Boolean,
    val productionSyncPresent: Boolean,
    val productionBackendClientPresent: Boolean,
    val productionProviderSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val productionSelectionStillDisabledProviderOnly: Boolean,
    val signingBroadcastingPresent: Boolean,
    val uiActionEnablementPresent: Boolean,
    val endpointPresent: Boolean,
    val mainnetPresent: Boolean,
    val vaultStoragePathImplementationAuthorizationPresent: Boolean,
    val vaultDirectoryCreationAuthorizationPresent: Boolean,
    val vaultFileReadAuthorizationPresent: Boolean,
    val vaultFileWriteAuthorizationPresent: Boolean,
    val vaultFileDeleteAuthorizationPresent: Boolean,
    val vaultSessionImplementationAuthorizationPresent: Boolean,
    val vaultUnlockAuthorizationPresent: Boolean,
    val vaultLockAuthorizationPresent: Boolean,
    val vaultContainerImplementationAuthorizationPresent: Boolean,
    val vaultContainerParserAuthorizationPresent: Boolean,
    val vaultContainerWriterAuthorizationPresent: Boolean,
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
    val vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly: Boolean,
    val androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation: Boolean,
    val linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation: Boolean,
    val appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation: Boolean,
    val noPlaintextCachePolicyAdmittedIsNotCacheImplementation: Boolean,
    val explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation: Boolean,
    val lockOnAppBackgroundPolicyAdmittedIsNotAndroidLifecycleImplementation: Boolean,
    val lockOnProcessDeathPolicyAdmittedIsNotRuntimeProcessLifecycleCode: Boolean,
    val sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling: Boolean,
    val platformWrappingPolicyReferenceAdmittedIsNotKeystoreOrKeyringImplementation: Boolean,
    val futureStoragePathsPlatformResolvedByReviewedCode: Boolean,
    val futureStoragePathsNotHardcodedInCommonCode: Boolean,
    val futureStoragePathsNotLogged: Boolean,
    val futureStoragePathsNotInSupportExports: Boolean,
    val futureStoragePathsNotInDisplayOutput: Boolean,
    val futureEncryptedVaultDataNotInNonSecretSettings: Boolean,
    val futureEncryptedVaultDataNotInAndroidSharedPreferences: Boolean,
    val futureEncryptedVaultDataNotInDesktopPlainConfig: Boolean,
    val futureBackupExportDestinationExplicitAndSeparate: Boolean,
    val futureUnlockMustBeExplicit: Boolean,
    val futureRuntimeSessionKeyMaterialMemoryOnly: Boolean,
    val futureRuntimeSessionKeyNeverSerialized: Boolean,
    val futureSessionLockOnExplicitUserAction: Boolean,
    val futureSessionLockOnProcessDeathOrRestart: Boolean,
    val futureAndroidBackgroundRequiresLockOrRevalidation: Boolean,
    val futureLinuxDesktopRequiresExplicitLockAndReviewedInactivityTimeout: Boolean,
    val androidKeystoreWrappingOptionalFutureOnly: Boolean,
    val linuxOsKeyringWrappingOptionalDeferred: Boolean,
    val platformWrappingDoesNotReplaceAppControlledVaultStorage: Boolean,
    val noDirectoryCreationInThisBranch: Boolean,
    val noFileReadWriteDeleteInThisBranch: Boolean,
    val noUnlockLockOrSessionImplementationInThisBranch: Boolean,
    val policyLabelsContainNoConcreteStorageStrings: Boolean,
    val lockSessionPolicyCreatesNoRuntimeSessionObject: Boolean,
    val lockSessionPolicyStoresNoKeyMaterial: Boolean,
    val platformWrappingImplementsNoAndroidKeystoreCall: Boolean,
    val platformWrappingImplementsNoLinuxKeyringCall: Boolean,
    val normalSourceMaterialGuardExcludesBuildHistory: Boolean,
    val localArtifactRootExcludedFromNormalSourceMaterialCorpus: Boolean,
    val storagePathSessionLifecycleDecisionPassed: Boolean,
    val evidenceCount: Int,
    val decisionCheckCount: Int,
    val blockerCount: Int,
    val warningCount: Int,
    val policyLabels: List<EncryptedVaultStoragePathSessionLifecyclePolicyLabel>,
    val decisionChecks: List<EncryptedVaultStoragePathSessionLifecycleDecisionCheck>,
    val failureLabels: List<EncryptedVaultStoragePathSessionLifecycleFailureLabel>,
    val displayLabel: EncryptedVaultStoragePathSessionLifecycleSafeLabel,
) {
    override fun toString(): String =
        "EncryptedVaultStoragePathSessionLifecycleDecision(" +
            "REDACTED, COMMON_MAIN_POLICY, STORAGE_PATH_SESSION_LIFECYCLE_DECISION_ONLY, " +
            "LATER_BRANCHES_ONLY, POLICY_LABELS_ONLY, NO_STORAGE_IO, " +
            "NO_DIRECTORY_CREATION, NO_RUNTIME_SESSION, NO_UNLOCK_LOCK_IMPLEMENTATION, " +
            "NO_SECURE_STORAGE_SUCCESS, NO_PRODUCTION_SYNC, DISABLED_PROVIDER_ONLY, " +
            "NO_SIGNING_BROADCASTING_UI_ENDPOINT_MAINNET" +
            ")"
}

object EncryptedVaultStoragePathSessionLifecycleDecisionPolicy {
    fun currentStoragePathSessionLifecycleDecision(): EncryptedVaultStoragePathSessionLifecycleDecision {
        val storageDecision =
            EncryptedVaultStorageReadinessDecisionPolicy.currentStorageReadinessDecision()
        val containerDecision =
            EncryptedVaultContainerFormatV1DecisionPolicy.currentContainerFormatV1Decision()
        val providerSelection = VaultCryptoProviderSelectionRegistry.select()
        val checks = EncryptedVaultStoragePathSessionLifecycleDecisionCheck.entries.toList()
        val policyLabels = EncryptedVaultStoragePathSessionLifecyclePolicyLabel.entries.toList()

        val storageReadinessDecisionPresent = storageDecision.storageReadinessDecisionPassed
        val containerFormatV1DecisionPresent = containerDecision.containerFormatV1DecisionPassed
        val encryptedVaultDesignPresent =
            storageDecision.encryptedVaultDesignPresent &&
                containerDecision.encryptedVaultDesignPresent
        val cryptoDecisionPresent =
            storageDecision.cryptoDecisionPresent &&
                containerDecision.cryptoDecisionPresent
        val dependencyReviewPresent =
            storageDecision.dependencyReviewPresent &&
                containerDecision.dependencyReviewPresent
        val providerBoundaryPresent =
            storageDecision.disabledVaultCryptoProviderBoundaryPresent &&
                containerDecision.vaultCryptoProviderBoundaryPresent
        val providerSelectionValidationCompletionAuditPresent =
            storageDecision.providerSelectionValidationCompletionAuditPassed &&
                containerDecision.providerSelectionValidationCompletionAuditPresent
        val secureStorageBoundaryPresent = storageDecision.secureStorageBoundaryPresent
        val secureMetadataBoundaryPresent = storageDecision.secureMetadataBoundaryPresent

        val vaultStoragePathSessionLifecycleDecisionAdmitted = true
        val androidAppPrivateStoragePolicyAdmitted = true
        val linuxUserDataStoragePolicyAdmitted = true
        val appControlledVaultDirectoryPolicyAdmitted = true
        val noPlaintextCachePolicyAdmitted = true
        val noNonSecretSettingsStorageForVaultPolicyAdmitted = true
        val backupExportStorageSeparationPolicyAdmitted = true
        val explicitUnlockRequiredPolicyAdmitted = true
        val lockOnAppBackgroundPolicyAdmitted = true
        val lockOnProcessDeathPolicyAdmitted = true
        val lockOnExplicitUserActionPolicyAdmitted = true
        val sessionKeyMemoryHandlingPolicyAdmitted = true
        val platformWrappingPolicyReferenceAdmitted = true

        val futureStoragePathImplementationRequiresSeparatePass = true
        val futureLockSessionImplementationRequiresSeparatePass = true
        val futureVaultContainerParserRequiresSeparatePass =
            containerDecision.futureVaultContainerParserRequiresSeparatePass
        val futureVaultContainerWriterRequiresSeparatePass =
            containerDecision.futureVaultContainerWriterRequiresSeparatePass
        val futureVaultStorageRepositoryRequiresSeparatePass =
            containerDecision.futureVaultStorageRepositoryRequiresSeparatePass
        val futureSecureStorageSuccessRequiresSeparatePass =
            storageDecision.futureSecureStorageSuccessRequiresSeparatePass
        val futureSecureMetadataSuccessRequiresSeparatePass =
            storageDecision.futureSecureMetadataSuccessRequiresSeparatePass
        val futureProductionSyncRequiresSeparatePass =
            storageDecision.futureProductionSyncRequiresSeparatePass
        val futureProductionProviderSelectionRequiresSeparatePass =
            storageDecision.futureProductionProviderSelectionRequiresSeparatePass

        val vaultStoragePathImplementationPresent = false
        val vaultDirectoryCreated = false
        val vaultFileReadPresent = false
        val vaultFileWritePresent = false
        val vaultFileDeletePresent = false
        val vaultContainerParserPresent = containerDecision.vaultContainerParserPresent
        val vaultContainerWriterPresent = containerDecision.vaultContainerWriterPresent
        val vaultContainerSerializationPresent = containerDecision.vaultContainerSerializationPresent
        val vaultContainerParsingPresent = containerDecision.vaultContainerParsingPresent
        val vaultContainerBytesProduced = containerDecision.vaultContainerBytesProduced
        val encryptedVaultFileFormatImplemented = containerDecision.encryptedVaultFileFormatImplemented
        val encryptedVaultRepositorySuccessPresent = containerDecision.encryptedVaultRepositorySuccessPresent
        val lockSessionImplementationPresent = false
        val unlockImplementationPresent = false
        val runtimeSessionKeyPresent = false
        val sessionKeyCached = false
        val plaintextCachePresent = false
        val nonSecretSettingsVaultStoragePresent = false
        val sharedPreferencesVaultStoragePresent = false
        val desktopConfigVaultStoragePresent = false
        val secureSecretStorageSuccessPathPresent = storageDecision.secureSecretStorageSuccessPathPresent
        val secureMetadataStorageSuccessPathPresent = storageDecision.secureMetadataStorageSuccessPathPresent
        val productionObservationPersistencePresent = storageDecision.productionObservationPersistencePresent
        val productionAddressIndexPersistencePresent = storageDecision.productionAddressIndexPersistencePresent
        val productionUtxoPersistencePresent = storageDecision.productionUtxoPersistencePresent
        val productionWalletHistoryPersistencePresent = storageDecision.productionWalletHistoryPersistencePresent
        val productionSyncPresent = storageDecision.productionSyncPresent
        val productionBackendClientPresent = storageDecision.productionBackendClientPresent
        val productionProviderSelectionEnabled = !providerSelection.selectedProviderIsDisabled
        val productionProviderSelectable = providerSelection.productionProviderSelectable
        val productionSelectionStillDisabledProviderOnly =
            providerSelection.selectedProviderIsDisabled &&
                !productionProviderSelectionEnabled &&
                !productionProviderSelectable
        val signingBroadcastingPresent = storageDecision.signingBroadcastingPresent
        val uiActionEnablementPresent = storageDecision.uiActionEnablementPresent
        val endpointPresent = storageDecision.endpointPresent
        val mainnetPresent = storageDecision.mainnetPresent

        val vaultStoragePathImplementationAuthorizationPresent = false
        val vaultDirectoryCreationAuthorizationPresent = false
        val vaultFileReadAuthorizationPresent = false
        val vaultFileWriteAuthorizationPresent = false
        val vaultFileDeleteAuthorizationPresent = false
        val vaultSessionImplementationAuthorizationPresent = false
        val vaultUnlockAuthorizationPresent = false
        val vaultLockAuthorizationPresent = false
        val vaultContainerImplementationAuthorizationPresent = false
        val vaultContainerParserAuthorizationPresent = false
        val vaultContainerWriterAuthorizationPresent = false
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

        val vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly =
            vaultStoragePathSessionLifecycleDecisionAdmitted &&
                futureStoragePathImplementationRequiresSeparatePass &&
                futureLockSessionImplementationRequiresSeparatePass &&
                !vaultStoragePathImplementationPresent &&
                !lockSessionImplementationPresent
        val androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation =
            androidAppPrivateStoragePolicyAdmitted &&
                !vaultStoragePathImplementationPresent &&
                !vaultDirectoryCreated
        val linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation =
            linuxUserDataStoragePolicyAdmitted &&
                !vaultStoragePathImplementationPresent &&
                !vaultDirectoryCreated
        val appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation =
            appControlledVaultDirectoryPolicyAdmitted &&
                !vaultDirectoryCreated &&
                !vaultFileWritePresent
        val noPlaintextCachePolicyAdmittedIsNotCacheImplementation =
            noPlaintextCachePolicyAdmitted &&
                !plaintextCachePresent &&
                !sessionKeyCached
        val explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation =
            explicitUnlockRequiredPolicyAdmitted &&
                !unlockImplementationPresent &&
                !vaultUnlockAuthorizationPresent
        val lockOnAppBackgroundPolicyAdmittedIsNotAndroidLifecycleImplementation =
            lockOnAppBackgroundPolicyAdmitted &&
                !lockSessionImplementationPresent &&
                !vaultLockAuthorizationPresent
        val lockOnProcessDeathPolicyAdmittedIsNotRuntimeProcessLifecycleCode =
            lockOnProcessDeathPolicyAdmitted &&
                !lockSessionImplementationPresent &&
                !runtimeSessionKeyPresent
        val sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling =
            sessionKeyMemoryHandlingPolicyAdmitted &&
                !runtimeSessionKeyPresent &&
                !sessionKeyCached
        val platformWrappingPolicyReferenceAdmittedIsNotKeystoreOrKeyringImplementation =
            platformWrappingPolicyReferenceAdmitted &&
                !productionSecretStorageAuthorizationPresent &&
                !productionMetadataStorageAuthorizationPresent

        val futureStoragePathsPlatformResolvedByReviewedCode = true
        val futureStoragePathsNotHardcodedInCommonCode = true
        val futureStoragePathsNotLogged = true
        val futureStoragePathsNotInSupportExports = true
        val futureStoragePathsNotInDisplayOutput = true
        val futureEncryptedVaultDataNotInNonSecretSettings = true
        val futureEncryptedVaultDataNotInAndroidSharedPreferences = true
        val futureEncryptedVaultDataNotInDesktopPlainConfig = true
        val futureBackupExportDestinationExplicitAndSeparate = true
        val futureUnlockMustBeExplicit = true
        val futureRuntimeSessionKeyMaterialMemoryOnly = true
        val futureRuntimeSessionKeyNeverSerialized = true
        val futureSessionLockOnExplicitUserAction = true
        val futureSessionLockOnProcessDeathOrRestart = true
        val futureAndroidBackgroundRequiresLockOrRevalidation = true
        val futureLinuxDesktopRequiresExplicitLockAndReviewedInactivityTimeout = true
        val androidKeystoreWrappingOptionalFutureOnly = true
        val linuxOsKeyringWrappingOptionalDeferred = true
        val platformWrappingDoesNotReplaceAppControlledVaultStorage = true
        val noDirectoryCreationInThisBranch = !vaultDirectoryCreated
        val noFileReadWriteDeleteInThisBranch =
            !vaultFileReadPresent &&
                !vaultFileWritePresent &&
                !vaultFileDeletePresent
        val noUnlockLockOrSessionImplementationInThisBranch =
            !unlockImplementationPresent &&
                !lockSessionImplementationPresent &&
                !runtimeSessionKeyPresent
        val policyLabelsContainNoConcreteStorageStrings = policyLabels.all { policy ->
            val label = policy.safeLabel.value
            "/" !in label &&
                "\\" !in label &&
                "." !in label &&
                "~" !in label
        }
        val lockSessionPolicyCreatesNoRuntimeSessionObject = !lockSessionImplementationPresent
        val lockSessionPolicyStoresNoKeyMaterial =
            !runtimeSessionKeyPresent &&
                !sessionKeyCached
        val platformWrappingImplementsNoAndroidKeystoreCall = true
        val platformWrappingImplementsNoLinuxKeyringCall = true
        val normalSourceMaterialGuardExcludesBuildHistory = true
        val localArtifactRootExcludedFromNormalSourceMaterialCorpus = true

        val existingEvidencePresent =
            storageReadinessDecisionPresent &&
                containerFormatV1DecisionPresent &&
                encryptedVaultDesignPresent &&
                cryptoDecisionPresent &&
                dependencyReviewPresent &&
                providerBoundaryPresent &&
                providerSelectionValidationCompletionAuditPresent &&
                secureStorageBoundaryPresent &&
                secureMetadataBoundaryPresent
        val storagePathSessionAdmissionPresent =
            vaultStoragePathSessionLifecycleDecisionAdmitted &&
                androidAppPrivateStoragePolicyAdmitted &&
                linuxUserDataStoragePolicyAdmitted &&
                appControlledVaultDirectoryPolicyAdmitted &&
                noPlaintextCachePolicyAdmitted &&
                noNonSecretSettingsStorageForVaultPolicyAdmitted &&
                backupExportStorageSeparationPolicyAdmitted &&
                explicitUnlockRequiredPolicyAdmitted &&
                lockOnAppBackgroundPolicyAdmitted &&
                lockOnProcessDeathPolicyAdmitted &&
                lockOnExplicitUserActionPolicyAdmitted &&
                sessionKeyMemoryHandlingPolicyAdmitted &&
                platformWrappingPolicyReferenceAdmitted
        val futureSeparatePassGatesPresent =
            futureStoragePathImplementationRequiresSeparatePass &&
                futureLockSessionImplementationRequiresSeparatePass &&
                futureVaultContainerParserRequiresSeparatePass &&
                futureVaultContainerWriterRequiresSeparatePass &&
                futureVaultStorageRepositoryRequiresSeparatePass &&
                futureSecureStorageSuccessRequiresSeparatePass &&
                futureSecureMetadataSuccessRequiresSeparatePass &&
                futureProductionSyncRequiresSeparatePass &&
                futureProductionProviderSelectionRequiresSeparatePass
        val storagePathRuntimeSurfacePresent =
            vaultStoragePathImplementationPresent ||
                vaultDirectoryCreated ||
                vaultFileReadPresent ||
                vaultFileWritePresent ||
                vaultFileDeletePresent ||
                nonSecretSettingsVaultStoragePresent ||
                sharedPreferencesVaultStoragePresent ||
                desktopConfigVaultStoragePresent
        val lockSessionRuntimeSurfacePresent =
            lockSessionImplementationPresent ||
                unlockImplementationPresent ||
                runtimeSessionKeyPresent ||
                sessionKeyCached ||
                plaintextCachePresent
        val containerRuntimeSurfacePresent =
            vaultContainerParserPresent ||
                vaultContainerWriterPresent ||
                vaultContainerSerializationPresent ||
                vaultContainerParsingPresent ||
                vaultContainerBytesProduced ||
                encryptedVaultFileFormatImplemented
        val productionStorageSurfacePresent =
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
            productionProviderSelectionEnabled || productionProviderSelectable
        val signingBroadcastingUiEndpointOrMainnetSurfacePresent =
            signingBroadcastingPresent ||
                uiActionEnablementPresent ||
                endpointPresent ||
                mainnetPresent
        val productionAuthorizationPresent =
            vaultStoragePathImplementationAuthorizationPresent ||
                vaultDirectoryCreationAuthorizationPresent ||
                vaultFileReadAuthorizationPresent ||
                vaultFileWriteAuthorizationPresent ||
                vaultFileDeleteAuthorizationPresent ||
                vaultSessionImplementationAuthorizationPresent ||
                vaultUnlockAuthorizationPresent ||
                vaultLockAuthorizationPresent ||
                vaultContainerImplementationAuthorizationPresent ||
                vaultContainerParserAuthorizationPresent ||
                vaultContainerWriterAuthorizationPresent ||
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
        val policyMaterialAbsent =
            policyLabelsContainNoConcreteStorageStrings &&
                lockSessionPolicyCreatesNoRuntimeSessionObject &&
                lockSessionPolicyStoresNoKeyMaterial &&
                platformWrappingImplementsNoAndroidKeystoreCall &&
                platformWrappingImplementsNoLinuxKeyringCall
        val corpusBoundaryPresent =
            normalSourceMaterialGuardExcludesBuildHistory &&
                localArtifactRootExcludedFromNormalSourceMaterialCorpus

        val failures = buildList {
            if (!existingEvidencePresent) {
                add(EncryptedVaultStoragePathSessionLifecycleFailureLabel.ExistingEvidenceMissing)
            }
            if (!storagePathSessionAdmissionPresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .StoragePathSessionAdmissionMissing,
                )
            }
            if (!futureSeparatePassGatesPresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .FutureSeparatePassGateMissing,
                )
            }
            if (storagePathRuntimeSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .StoragePathRuntimeSurfacePresent,
                )
            }
            if (lockSessionRuntimeSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .LockSessionRuntimeSurfacePresent,
                )
            }
            if (containerRuntimeSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .ContainerRuntimeSurfacePresent,
                )
            }
            if (productionStorageSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .ProductionStorageSurfacePresent,
                )
            }
            if (productionSyncSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .ProductionSyncSurfacePresent,
                )
            }
            if (productionProviderSelectionSurfacePresent || !productionSelectionStillDisabledProviderOnly) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .ProductionProviderSelectionSurfacePresent,
                )
            }
            if (signingBroadcastingUiEndpointOrMainnetSurfacePresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .SigningBroadcastingUiEndpointOrMainnetSurfacePresent,
                )
            }
            if (productionAuthorizationPresent) {
                add(
                    EncryptedVaultStoragePathSessionLifecycleFailureLabel
                        .ProductionAuthorizationPresent,
                )
            }
            if (!policyMaterialAbsent) {
                add(EncryptedVaultStoragePathSessionLifecycleFailureLabel.PolicyMaterialPresent)
            }
            if (!corpusBoundaryPresent) {
                add(EncryptedVaultStoragePathSessionLifecycleFailureLabel.CorpusBoundaryMissing)
            }
        }

        val storagePathSessionLifecycleDecisionPassed =
            failures.isEmpty() &&
                vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly &&
                androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation &&
                linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation &&
                appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation &&
                explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation &&
                sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling

        return EncryptedVaultStoragePathSessionLifecycleDecision(
            decisionId = EncryptedVaultStoragePathSessionLifecycleSafeLabel(
                "skald-encrypted-local-vault-storage-path-session-lifecycle-decision-v1",
            ),
            decisionVersion = 1,
            decisionKind =
                EncryptedVaultStoragePathSessionLifecycleDecisionKind
                    .EncryptedLocalVaultStoragePathSessionLifecycleDecision,
            sourceSet = EncryptedVaultStoragePathSessionLifecycleDecisionSourceSet.CommonMainPolicy,
            storageReadinessDecisionPresent = storageReadinessDecisionPresent,
            containerFormatV1DecisionPresent = containerFormatV1DecisionPresent,
            encryptedVaultDesignPresent = encryptedVaultDesignPresent,
            cryptoDecisionPresent = cryptoDecisionPresent,
            dependencyReviewPresent = dependencyReviewPresent,
            providerBoundaryPresent = providerBoundaryPresent,
            providerSelectionValidationCompletionAuditPresent =
                providerSelectionValidationCompletionAuditPresent,
            secureStorageBoundaryPresent = secureStorageBoundaryPresent,
            secureMetadataBoundaryPresent = secureMetadataBoundaryPresent,
            vaultStoragePathSessionLifecycleDecisionAdmitted =
                vaultStoragePathSessionLifecycleDecisionAdmitted,
            androidAppPrivateStoragePolicyAdmitted = androidAppPrivateStoragePolicyAdmitted,
            linuxUserDataStoragePolicyAdmitted = linuxUserDataStoragePolicyAdmitted,
            appControlledVaultDirectoryPolicyAdmitted = appControlledVaultDirectoryPolicyAdmitted,
            noPlaintextCachePolicyAdmitted = noPlaintextCachePolicyAdmitted,
            noNonSecretSettingsStorageForVaultPolicyAdmitted =
                noNonSecretSettingsStorageForVaultPolicyAdmitted,
            backupExportStorageSeparationPolicyAdmitted = backupExportStorageSeparationPolicyAdmitted,
            explicitUnlockRequiredPolicyAdmitted = explicitUnlockRequiredPolicyAdmitted,
            lockOnAppBackgroundPolicyAdmitted = lockOnAppBackgroundPolicyAdmitted,
            lockOnProcessDeathPolicyAdmitted = lockOnProcessDeathPolicyAdmitted,
            lockOnExplicitUserActionPolicyAdmitted = lockOnExplicitUserActionPolicyAdmitted,
            sessionKeyMemoryHandlingPolicyAdmitted = sessionKeyMemoryHandlingPolicyAdmitted,
            platformWrappingPolicyReferenceAdmitted = platformWrappingPolicyReferenceAdmitted,
            futureStoragePathImplementationRequiresSeparatePass =
                futureStoragePathImplementationRequiresSeparatePass,
            futureLockSessionImplementationRequiresSeparatePass =
                futureLockSessionImplementationRequiresSeparatePass,
            futureVaultContainerParserRequiresSeparatePass =
                futureVaultContainerParserRequiresSeparatePass,
            futureVaultContainerWriterRequiresSeparatePass =
                futureVaultContainerWriterRequiresSeparatePass,
            futureVaultStorageRepositoryRequiresSeparatePass =
                futureVaultStorageRepositoryRequiresSeparatePass,
            futureSecureStorageSuccessRequiresSeparatePass =
                futureSecureStorageSuccessRequiresSeparatePass,
            futureSecureMetadataSuccessRequiresSeparatePass =
                futureSecureMetadataSuccessRequiresSeparatePass,
            futureProductionSyncRequiresSeparatePass = futureProductionSyncRequiresSeparatePass,
            futureProductionProviderSelectionRequiresSeparatePass =
                futureProductionProviderSelectionRequiresSeparatePass,
            vaultStoragePathImplementationPresent = vaultStoragePathImplementationPresent,
            vaultDirectoryCreated = vaultDirectoryCreated,
            vaultFileReadPresent = vaultFileReadPresent,
            vaultFileWritePresent = vaultFileWritePresent,
            vaultFileDeletePresent = vaultFileDeletePresent,
            vaultContainerParserPresent = vaultContainerParserPresent,
            vaultContainerWriterPresent = vaultContainerWriterPresent,
            vaultContainerSerializationPresent = vaultContainerSerializationPresent,
            vaultContainerParsingPresent = vaultContainerParsingPresent,
            vaultContainerBytesProduced = vaultContainerBytesProduced,
            encryptedVaultFileFormatImplemented = encryptedVaultFileFormatImplemented,
            encryptedVaultRepositorySuccessPresent = encryptedVaultRepositorySuccessPresent,
            lockSessionImplementationPresent = lockSessionImplementationPresent,
            unlockImplementationPresent = unlockImplementationPresent,
            runtimeSessionKeyPresent = runtimeSessionKeyPresent,
            sessionKeyCached = sessionKeyCached,
            plaintextCachePresent = plaintextCachePresent,
            nonSecretSettingsVaultStoragePresent = nonSecretSettingsVaultStoragePresent,
            sharedPreferencesVaultStoragePresent = sharedPreferencesVaultStoragePresent,
            desktopConfigVaultStoragePresent = desktopConfigVaultStoragePresent,
            secureSecretStorageSuccessPathPresent = secureSecretStorageSuccessPathPresent,
            secureMetadataStorageSuccessPathPresent = secureMetadataStorageSuccessPathPresent,
            productionObservationPersistencePresent = productionObservationPersistencePresent,
            productionAddressIndexPersistencePresent = productionAddressIndexPersistencePresent,
            productionUtxoPersistencePresent = productionUtxoPersistencePresent,
            productionWalletHistoryPersistencePresent = productionWalletHistoryPersistencePresent,
            productionSyncPresent = productionSyncPresent,
            productionBackendClientPresent = productionBackendClientPresent,
            productionProviderSelectionEnabled = productionProviderSelectionEnabled,
            productionProviderSelectable = productionProviderSelectable,
            productionSelectionStillDisabledProviderOnly = productionSelectionStillDisabledProviderOnly,
            signingBroadcastingPresent = signingBroadcastingPresent,
            uiActionEnablementPresent = uiActionEnablementPresent,
            endpointPresent = endpointPresent,
            mainnetPresent = mainnetPresent,
            vaultStoragePathImplementationAuthorizationPresent =
                vaultStoragePathImplementationAuthorizationPresent,
            vaultDirectoryCreationAuthorizationPresent = vaultDirectoryCreationAuthorizationPresent,
            vaultFileReadAuthorizationPresent = vaultFileReadAuthorizationPresent,
            vaultFileWriteAuthorizationPresent = vaultFileWriteAuthorizationPresent,
            vaultFileDeleteAuthorizationPresent = vaultFileDeleteAuthorizationPresent,
            vaultSessionImplementationAuthorizationPresent =
                vaultSessionImplementationAuthorizationPresent,
            vaultUnlockAuthorizationPresent = vaultUnlockAuthorizationPresent,
            vaultLockAuthorizationPresent = vaultLockAuthorizationPresent,
            vaultContainerImplementationAuthorizationPresent =
                vaultContainerImplementationAuthorizationPresent,
            vaultContainerParserAuthorizationPresent = vaultContainerParserAuthorizationPresent,
            vaultContainerWriterAuthorizationPresent = vaultContainerWriterAuthorizationPresent,
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
            vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly =
                vaultStoragePathSessionLifecycleDecisionAdmittedIsLaterBranchOnly,
            androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation =
                androidAppPrivateStoragePolicyAdmittedIsNotAndroidStorageImplementation,
            linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation =
                linuxUserDataStoragePolicyAdmittedIsNotLinuxStorageImplementation,
            appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation =
                appControlledVaultDirectoryPolicyAdmittedIsNotDirectoryCreation,
            noPlaintextCachePolicyAdmittedIsNotCacheImplementation =
                noPlaintextCachePolicyAdmittedIsNotCacheImplementation,
            explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation =
                explicitUnlockRequiredPolicyAdmittedIsNotUnlockImplementation,
            lockOnAppBackgroundPolicyAdmittedIsNotAndroidLifecycleImplementation =
                lockOnAppBackgroundPolicyAdmittedIsNotAndroidLifecycleImplementation,
            lockOnProcessDeathPolicyAdmittedIsNotRuntimeProcessLifecycleCode =
                lockOnProcessDeathPolicyAdmittedIsNotRuntimeProcessLifecycleCode,
            sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling =
                sessionKeyMemoryHandlingPolicyAdmittedIsNotRuntimeSessionKeyHandling,
            platformWrappingPolicyReferenceAdmittedIsNotKeystoreOrKeyringImplementation =
                platformWrappingPolicyReferenceAdmittedIsNotKeystoreOrKeyringImplementation,
            futureStoragePathsPlatformResolvedByReviewedCode =
                futureStoragePathsPlatformResolvedByReviewedCode,
            futureStoragePathsNotHardcodedInCommonCode = futureStoragePathsNotHardcodedInCommonCode,
            futureStoragePathsNotLogged = futureStoragePathsNotLogged,
            futureStoragePathsNotInSupportExports = futureStoragePathsNotInSupportExports,
            futureStoragePathsNotInDisplayOutput = futureStoragePathsNotInDisplayOutput,
            futureEncryptedVaultDataNotInNonSecretSettings =
                futureEncryptedVaultDataNotInNonSecretSettings,
            futureEncryptedVaultDataNotInAndroidSharedPreferences =
                futureEncryptedVaultDataNotInAndroidSharedPreferences,
            futureEncryptedVaultDataNotInDesktopPlainConfig =
                futureEncryptedVaultDataNotInDesktopPlainConfig,
            futureBackupExportDestinationExplicitAndSeparate =
                futureBackupExportDestinationExplicitAndSeparate,
            futureUnlockMustBeExplicit = futureUnlockMustBeExplicit,
            futureRuntimeSessionKeyMaterialMemoryOnly = futureRuntimeSessionKeyMaterialMemoryOnly,
            futureRuntimeSessionKeyNeverSerialized = futureRuntimeSessionKeyNeverSerialized,
            futureSessionLockOnExplicitUserAction = futureSessionLockOnExplicitUserAction,
            futureSessionLockOnProcessDeathOrRestart = futureSessionLockOnProcessDeathOrRestart,
            futureAndroidBackgroundRequiresLockOrRevalidation =
                futureAndroidBackgroundRequiresLockOrRevalidation,
            futureLinuxDesktopRequiresExplicitLockAndReviewedInactivityTimeout =
                futureLinuxDesktopRequiresExplicitLockAndReviewedInactivityTimeout,
            androidKeystoreWrappingOptionalFutureOnly = androidKeystoreWrappingOptionalFutureOnly,
            linuxOsKeyringWrappingOptionalDeferred = linuxOsKeyringWrappingOptionalDeferred,
            platformWrappingDoesNotReplaceAppControlledVaultStorage =
                platformWrappingDoesNotReplaceAppControlledVaultStorage,
            noDirectoryCreationInThisBranch = noDirectoryCreationInThisBranch,
            noFileReadWriteDeleteInThisBranch = noFileReadWriteDeleteInThisBranch,
            noUnlockLockOrSessionImplementationInThisBranch =
                noUnlockLockOrSessionImplementationInThisBranch,
            policyLabelsContainNoConcreteStorageStrings = policyLabelsContainNoConcreteStorageStrings,
            lockSessionPolicyCreatesNoRuntimeSessionObject =
                lockSessionPolicyCreatesNoRuntimeSessionObject,
            lockSessionPolicyStoresNoKeyMaterial = lockSessionPolicyStoresNoKeyMaterial,
            platformWrappingImplementsNoAndroidKeystoreCall =
                platformWrappingImplementsNoAndroidKeystoreCall,
            platformWrappingImplementsNoLinuxKeyringCall =
                platformWrappingImplementsNoLinuxKeyringCall,
            normalSourceMaterialGuardExcludesBuildHistory = normalSourceMaterialGuardExcludesBuildHistory,
            localArtifactRootExcludedFromNormalSourceMaterialCorpus =
                localArtifactRootExcludedFromNormalSourceMaterialCorpus,
            storagePathSessionLifecycleDecisionPassed = storagePathSessionLifecycleDecisionPassed,
            evidenceCount = 9,
            decisionCheckCount = checks.size,
            blockerCount = failures.size,
            warningCount = 0,
            policyLabels = policyLabels,
            decisionChecks = checks,
            failureLabels = failures,
            displayLabel = EncryptedVaultStoragePathSessionLifecycleSafeLabel(
                "encrypted vault storage path session lifecycle decision",
            ),
        )
    }
}
