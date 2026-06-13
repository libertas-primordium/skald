package com.libertasprimordium.skald.security

interface SkaldVaultV1AuthorizationReadinessMatrixBoundary {
    fun evaluate(
        request: SkaldVaultV1AuthorizationReadinessMatrixRequest,
    ): SkaldVaultV1AuthorizationReadinessMatrixResult<SkaldVaultV1AuthorizationReadinessMatrixEvidence>
}

enum class SkaldVaultV1AuthorizationReadinessMatrixSource(val label: String) {
    CurrentTypedEvidence("current typed boundary evidence"),
    CapabilityAudit("capability audit request"),
    BoundaryTrace("boundary trace request"),
}

enum class SkaldVaultV1AuthorizationReadinessMatrixStatus(val label: String) {
    BlockedFailClosed("blocked fail closed"),
    DisabledByPolicy("disabled by policy"),
    EvidenceOnly("evidence only"),
    FutureReviewRequired("future review required"),
    TestOnlyRejectedForProduction("test-only evidence rejected for production"),
    WarningOnlyCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
    UnsupportedFailClosed("unsupported fail closed"),
}

enum class SkaldVaultV1AuthorizationReadinessCapabilityId(val label: String) {
    ProductionProviderSelectability("production provider selectability"),
    ProviderOperationExecution("provider operation execution"),
    RuntimeRandomnessAuthorization("runtime randomness authorization"),
    KdfCalibrationFinalParameterApproval("KDF calibration and final parameter approval"),
    SecureSecretStorage("secure secret storage"),
    SecureMetadataStorage("secure metadata storage"),
    VaultCreation("vault creation"),
    VaultUnlock("vault unlock"),
    ActiveSession("active session"),
    VaultPersistence("vault persistence"),
    EncryptedLocalVaultStorage("encrypted local vault storage"),
    ManifestReadWrite("manifest read/write"),
    StorageIndexReadWrite("storage-index read/write"),
    RecordReadWrite("record read/write"),
    AtomicWrite("atomic write"),
    CrashRecovery("crash recovery"),
    Migration("migration"),
    CorruptionRecovery("corruption recovery"),
    RollbackProtection("rollback protection"),
    ClearWipeImplementation("clear/wipe implementation"),
    RedactionSafeDiagnostics("redaction-safe diagnostics"),
    PassphraseInput("passphrase input"),
    PassphraseRetryThrottle("passphrase retry/throttle"),
    AndroidAppPrivateStorageReadiness("Android app-private storage readiness"),
    AndroidLifecycleReadiness("Android lifecycle readiness"),
    AndroidKeystoreOptionalWrapping("Android Keystore optional wrapping"),
    LinuxRootReadiness("Linux root readiness"),
    LinuxOptionalKeyWrapping("Linux optional key wrapping"),
    BdkPersistence("BDK persistence"),
    WalletSync("wallet sync"),
    Signing("signing"),
    Broadcasting("broadcasting"),
    TorTransport("Tor transport"),
    NostrParsing("Nostr parsing"),
    Mainnet("mainnet"),
}

enum class SkaldVaultV1AuthorizationReadinessBoundaryId(val label: String) {
    ProviderSelectionBoundary("provider selection boundary"),
    ProductionProviderAcceptanceContract("production provider acceptance contract"),
    DependencyProbe("dependency probe"),
    ProviderOperationAuthorizationBoundary("provider operation authorization boundary"),
    RuntimeRandomnessAuthorizationBoundary("runtime randomness authorization boundary"),
    KdfCalibrationAuthorizationBoundary("KDF calibration authorization boundary"),
    SecureStorageAuthorizationBoundary("secure-storage authorization boundary"),
    CreationAuthorizationBoundary("creation authorization boundary"),
    UnlockAuthorizationBoundary("unlock authorization boundary"),
    LockSessionLifecycleBoundary("lock/session lifecycle boundary"),
    PersistenceReadinessGate("persistence readiness gate"),
    DisabledStorageServiceFacade("disabled storage service facade"),
    StorageSafetyPreflightBoundary("storage safety preflight boundary"),
    PlatformRootResolverBoundary("platform root resolver boundary"),
    PlatformPathConstructionBoundary("platform path-construction boundary"),
    LinuxRootResolutionPolicy("Linux root-resolution policy"),
    LinuxCustomRootValidationPolicy("Linux custom-root validation policy"),
    PlatformRootSettingsPolicy("platform root settings policy"),
    SecureStorageBoundary("secure storage boundary"),
    SecureMetadataBoundary("secure metadata boundary"),
    PassphrasePolicyBoundary("passphrase policy boundary"),
    ClearWipeStrategyBoundary("clear/wipe strategy boundary"),
    RedactionLeakageBoundary("redaction/leakage boundary"),
    MigrationCorruptionBoundary("migration/corruption boundary"),
    DisabledProviderFacade("disabled provider facade"),
    EncryptedVaultReadinessPolicy("encrypted vault readiness policy"),
    BdkBoundary("BDK boundary"),
    WalletOutOfScopeBoundary("wallet out-of-scope boundary"),
    MainnetPolicy("mainnet policy"),
    AndroidPolicy("Android policy"),
    LinuxPolicy("Linux policy"),
}

enum class SkaldVaultV1AuthorizationReadinessBlockerCategory(val label: String) {
    DisabledProviderSelection("disabled provider selection"),
    ProductionProviderSelectableFalse("productionProviderSelectable is false"),
    ProviderImplementationMissing("provider implementation missing"),
    ProviderKatsNotApproved("provider KATs not approved"),
    ProviderOperationAuthorizationBlocked("provider operation authorization blocked"),
    RuntimeRandomnessAuthorizationBlocked("runtime randomness authorization blocked"),
    KdfCalibrationAuthorizationBlocked("KDF calibration authorization blocked"),
    SecureStorageAuthorizationBlocked("secure-storage authorization blocked"),
    SecureSecretStorageUnavailable("secure secret storage unavailable"),
    SecureMetadataStorageUnavailable("secure metadata storage unavailable"),
    PassphraseInputBlocked("passphrase input blocked"),
    LockSessionLifecycleUnavailable("lock/session lifecycle unavailable"),
    ClearWipeStrategyModelOnly("clear/wipe strategy model-only"),
    RedactionLeakageModelOnly("redaction/leakage non-logging model-only"),
    MigrationCorruptionModelOnly("migration/corruption model-only"),
    StorageSafetyPreflightModelOnly("storage safety preflight model-only"),
    DisabledStorageServiceFacade("disabled storage service facade"),
    PersistenceReadinessBlocked("persistence readiness blocked"),
    RootPathEvidenceModelOnly("root/path evidence model-only"),
    SettingsRootUsabilityReviewMissing("Settings/root usability review missing"),
    AndroidLifecycleReviewMissing("Android lifecycle review missing"),
    LinuxLifecycleReviewMissing("Linux lifecycle review missing"),
    AndroidKeystoreWrappingReviewMissing("Android Keystore wrapping review missing"),
    LinuxOptionalWrappingReviewMissing("Linux optional wrapping review missing"),
    BdkPersistenceNotReviewed("BDK persistence not reviewed"),
    WalletSyncSigningOutOfScope("wallet/sync/signing not in scope"),
    MainnetDisabled("mainnet disabled"),
    TestOnlyEvidenceRejectedForProduction("test-only evidence rejected for production"),
    WarningOnlyEvidenceCannotAuthorize("warning-only evidence cannot authorize"),
    UserConsentCannotOverride("user consent cannot override"),
}

enum class SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence(val label: String) {
    ProductionProviderImplementationApproved("production provider implementation approved"),
    ProviderKatsApproved("provider KATs approved"),
    ProviderOperationAuthorizationApproved("provider operation authorization approved"),
    RuntimeRandomnessAuthorizationApproved("runtime randomness authorization approved"),
    KdfCalibrationFinalParametersApproved("KDF calibration and final parameters approved"),
    SecureStorageAuthorizationApproved("secure-storage authorization approved"),
    SecureSecretStorageAvailable("secure secret storage available"),
    SecureMetadataStorageAvailable("secure metadata storage available"),
    CreationAuthorizationApproved("creation authorization approved"),
    UnlockAuthorizationApproved("unlock authorization approved"),
    LockSessionLifecycleApproved("lock/session lifecycle approved"),
    PersistenceReadinessApproved("persistence readiness approved"),
    StorageServiceImplementedAndApproved("storage service implemented and approved"),
    StorageSafetyPreflightApproved("storage safety preflight approved"),
    PlatformRootPathSafetyApproved("platform root/path safety approved"),
    ManifestStorageIndexRecordImplementationApproved(
        "manifest, storage-index, and record implementation approved",
    ),
    AtomicWriteCrashRecoveryApproved("atomic write and crash recovery approved"),
    MigrationCorruptionRuntimeApproved("migration/corruption runtime handling approved"),
    ClearWipeRuntimeApproved("clear/wipe runtime handling approved"),
    RedactionLeakageRuntimeApproved("redaction/leakage runtime handling approved"),
    PassphraseInputAndRetryApproved("passphrase input and retry/throttle approved"),
    AndroidLifecycleApproved("Android lifecycle approved"),
    LinuxLifecycleApproved("Linux lifecycle approved"),
    AndroidKeystoreWrapperReviewApproved("Android Keystore wrapper review approved"),
    LinuxOptionalWrapperReviewApproved("Linux optional wrapper review approved"),
    BdkPersistenceReviewed("BDK persistence reviewed"),
    WalletSyncSigningBroadcastingReviewed("wallet sync, signing, and broadcasting reviewed"),
    TorNostrReviewed("Tor and Nostr reviewed"),
    MainnetReleaseApproved("mainnet release approved"),
}

enum class SkaldVaultV1AuthorizationReadinessRedactionClass(val label: String) {
    PolicyIdsOnly("policy ids only"),
    BoundaryAndBlockerClassesOnly("boundary and blocker classes only"),
    CapabilityStatusOnly("capability status only"),
    PlatformEvidenceOnly("platform evidence only"),
    NoDiagnosticPayload("no diagnostic payload"),
}

data class SkaldVaultV1AuthorizationReadinessMatrixCapability(
    val providerSelectable: Boolean,
    val productionProviderSelectable: Boolean,
    val providerOperationAuthorized: Boolean,
    val runtimeRandomnessReady: Boolean,
    val kdfReady: Boolean,
    val secureStorageReady: Boolean,
    val secureSecretStorageAvailable: Boolean,
    val secureMetadataStorageAvailable: Boolean,
    val creationReady: Boolean,
    val unlockReady: Boolean,
    val activeSessionReady: Boolean,
    val persistenceReady: Boolean,
    val storageServiceAvailable: Boolean,
    val vaultStorageAvailable: Boolean,
    val manifestReadWriteAvailable: Boolean,
    val storageIndexReadWriteAvailable: Boolean,
    val recordReadWriteAvailable: Boolean,
    val atomicWriteAvailable: Boolean,
    val crashRecoveryAvailable: Boolean,
    val migrationAvailable: Boolean,
    val corruptionRecoveryAvailable: Boolean,
    val rollbackProtectionAvailable: Boolean,
    val clearWipeAvailable: Boolean,
    val redactionSafeDiagnosticsAvailable: Boolean,
    val passphraseInputAccepted: Boolean,
    val passphraseRetryThrottleAvailable: Boolean,
    val androidAppPrivateStorageReady: Boolean,
    val androidLifecycleReady: Boolean,
    val androidKeystoreWrappingAvailable: Boolean,
    val linuxRootReady: Boolean,
    val linuxOptionalWrappingAvailable: Boolean,
    val bdkPersistenceAvailable: Boolean,
    val walletSyncAvailable: Boolean,
    val signingAvailable: Boolean,
    val broadcastingAvailable: Boolean,
    val torTransportAvailable: Boolean,
    val nostrParsingAvailable: Boolean,
    val mainnetAvailable: Boolean,
) {
    companion object {
        val StillDisabled = SkaldVaultV1AuthorizationReadinessMatrixCapability(
            providerSelectable = false,
            productionProviderSelectable = false,
            providerOperationAuthorized = false,
            runtimeRandomnessReady = false,
            kdfReady = false,
            secureStorageReady = false,
            secureSecretStorageAvailable = false,
            secureMetadataStorageAvailable = false,
            creationReady = false,
            unlockReady = false,
            activeSessionReady = false,
            persistenceReady = false,
            storageServiceAvailable = false,
            vaultStorageAvailable = false,
            manifestReadWriteAvailable = false,
            storageIndexReadWriteAvailable = false,
            recordReadWriteAvailable = false,
            atomicWriteAvailable = false,
            crashRecoveryAvailable = false,
            migrationAvailable = false,
            corruptionRecoveryAvailable = false,
            rollbackProtectionAvailable = false,
            clearWipeAvailable = false,
            redactionSafeDiagnosticsAvailable = false,
            passphraseInputAccepted = false,
            passphraseRetryThrottleAvailable = false,
            androidAppPrivateStorageReady = false,
            androidLifecycleReady = false,
            androidKeystoreWrappingAvailable = false,
            linuxRootReady = false,
            linuxOptionalWrappingAvailable = false,
            bdkPersistenceAvailable = false,
            walletSyncAvailable = false,
            signingAvailable = false,
            broadcastingAvailable = false,
            torTransportAvailable = false,
            nostrParsingAvailable = false,
            mainnetAvailable = false,
        )
    }
}

class SkaldVaultV1AuthorizationReadinessMatrixPolicyToken private constructor(
    val policyId: String,
    val capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId?,
) {
    val containsSecretMaterial: Boolean = false
    val containsPathOrRootText: Boolean = false
    val containsProviderHandle: Boolean = false
    val containsRecordIdentifier: Boolean = false
    val containsByteMaterial: Boolean = false

    override fun toString(): String =
        "SkaldVaultV1AuthorizationReadinessMatrixPolicyToken(" +
            "policyId=$policyId, " +
            "capabilityId=$capabilityId, " +
            "secretMaterial=<redacted>, " +
            "pathOrRoot=<redacted>, " +
            "providerHandle=<redacted>, " +
            "byteMaterial=<redacted>" +
            ")"

    companion object {
        fun redacted(
            policyId: String,
            capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId?,
        ): SkaldVaultV1AuthorizationReadinessMatrixPolicyToken {
            return SkaldVaultV1AuthorizationReadinessMatrixPolicyToken(policyId, capabilityId)
        }
    }
}

class SkaldVaultV1AuthorizationReadinessMatrixRequest private constructor(
    val source: SkaldVaultV1AuthorizationReadinessMatrixSource,
    val capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId?,
    private val providerSelectionResult: VaultCryptoProviderSelectionResult?,
    private val providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment?,
    private val dependencyProbeResult: VaultCryptoDependencyProbeResult?,
    private val encryptedVaultReadiness: EncryptedVaultReadiness?,
) {
    val providerSelectionEvidenceSupplied: Boolean
        get() = providerSelectionResult != null

    val providerAcceptanceEvidenceSupplied: Boolean
        get() = providerAcceptanceAssessment != null

    val dependencyProbeEvidenceSupplied: Boolean
        get() = dependencyProbeResult != null

    val encryptedVaultReadinessEvidenceSupplied: Boolean
        get() = encryptedVaultReadiness != null

    override fun toString(): String =
        "SkaldVaultV1AuthorizationReadinessMatrixRequest(" +
            "source=$source, " +
            "capabilityId=$capabilityId, " +
            "providerSelection=<redacted>, " +
            "providerAcceptance=<redacted>, " +
            "dependencyProbe=<redacted>, " +
            "encryptedVaultReadiness=<redacted>" +
            ")"

    companion object {
        fun currentEvidence(
            providerSelectionResult: VaultCryptoProviderSelectionResult? = null,
            providerAcceptanceAssessment: ProductionProviderAcceptanceAssessment? = null,
            dependencyProbeResult: VaultCryptoDependencyProbeResult? = null,
            encryptedVaultReadiness: EncryptedVaultReadiness? = null,
        ): SkaldVaultV1AuthorizationReadinessMatrixRequest =
            SkaldVaultV1AuthorizationReadinessMatrixRequest(
                source = SkaldVaultV1AuthorizationReadinessMatrixSource.CurrentTypedEvidence,
                capabilityId = null,
                providerSelectionResult = providerSelectionResult,
                providerAcceptanceAssessment = providerAcceptanceAssessment,
                dependencyProbeResult = dependencyProbeResult,
                encryptedVaultReadiness = encryptedVaultReadiness,
            )

        fun forCapability(
            capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        ): SkaldVaultV1AuthorizationReadinessMatrixRequest =
            SkaldVaultV1AuthorizationReadinessMatrixRequest(
                source = SkaldVaultV1AuthorizationReadinessMatrixSource.CapabilityAudit,
                capabilityId = capabilityId,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                encryptedVaultReadiness = null,
            )

        fun boundaryTrace(): SkaldVaultV1AuthorizationReadinessMatrixRequest =
            SkaldVaultV1AuthorizationReadinessMatrixRequest(
                source = SkaldVaultV1AuthorizationReadinessMatrixSource.BoundaryTrace,
                capabilityId = null,
                providerSelectionResult = null,
                providerAcceptanceAssessment = null,
                dependencyProbeResult = null,
                encryptedVaultReadiness = null,
            )
    }
}

data class SkaldVaultV1AuthorizationReadinessCapabilityRow(
    val capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
    val currentStatuses: Set<SkaldVaultV1AuthorizationReadinessMatrixStatus>,
    val blockingBoundaries: Set<SkaldVaultV1AuthorizationReadinessBoundaryId>,
    val blockerCategories: Set<SkaldVaultV1AuthorizationReadinessBlockerCategory>,
    val requiredFutureEvidence: Set<SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence>,
    val prohibitedAccidentalReadinessFlags: Set<String>,
    val userConsentCanOverride: Boolean,
    val warningOnlyEvidenceCanAuthorize: Boolean,
    val testOnlyEvidenceCanAuthorizeProduction: Boolean,
    val mainnetAllowed: Boolean,
    val currentlyReady: Boolean,
    val runtimeAvailable: Boolean,
    val redactionClass: SkaldVaultV1AuthorizationReadinessRedactionClass,
)

data class SkaldVaultV1AuthorizationReadinessPolicySummary(
    val policyId: String,
    val policyVersion: Int,
    val statuses: Set<SkaldVaultV1AuthorizationReadinessMatrixStatus>,
    val capabilityIds: Set<SkaldVaultV1AuthorizationReadinessCapabilityId>,
    val boundaryIds: Set<SkaldVaultV1AuthorizationReadinessBoundaryId>,
    val blockerCategories: Set<SkaldVaultV1AuthorizationReadinessBlockerCategory>,
    val requiredFutureEvidence: Set<SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence>,
    val redactionClasses: Set<SkaldVaultV1AuthorizationReadinessRedactionClass>,
    val capability: SkaldVaultV1AuthorizationReadinessMatrixCapability,
    val stillDisabled: Boolean,
    val evidenceOnly: Boolean,
    val allProductionRuntimeCapabilitiesBlocked: Boolean,
)

data class SkaldVaultV1AuthorizationReadinessMatrixEvidence(
    val policyId: String,
    val policyVersion: Int,
    val source: SkaldVaultV1AuthorizationReadinessMatrixSource,
    val status: SkaldVaultV1AuthorizationReadinessMatrixStatus,
    val capability: SkaldVaultV1AuthorizationReadinessMatrixCapability,
    val capabilityRows: List<SkaldVaultV1AuthorizationReadinessCapabilityRow>,
    val blockers: Set<SkaldVaultV1AuthorizationReadinessBlockerCategory>,
    val blockingBoundaries: Set<SkaldVaultV1AuthorizationReadinessBoundaryId>,
    val requiredFutureEvidence: Set<SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence>,
    val policyTokenEvidence: SkaldVaultV1AuthorizationReadinessMatrixPolicyToken,
    val providerSelectionEvidenceConsumed: Boolean,
    val providerAcceptanceEvidenceConsumed: Boolean,
    val dependencyProbeEvidenceConsumed: Boolean,
    val encryptedVaultReadinessEvidenceConsumed: Boolean,
    val authorizationReadinessMatrixModeled: Boolean = true,
    val authorizationReadinessMatrixStillDisabled: Boolean = true,
    val authorizationReadinessMatrixBlocksAllRuntimeCapabilities: Boolean = true,
    val authorizationReadinessMatrixDoesNotEnableCreation: Boolean = true,
    val authorizationReadinessMatrixDoesNotEnableUnlock: Boolean = true,
    val authorizationReadinessMatrixDoesNotEnablePersistence: Boolean = true,
    val authorizationReadinessMatrixDoesNotEnableProviderSelection: Boolean = true,
    val authorizationReadinessMatrixFailureVocabularyModeled: Boolean = true,
    val providerSelectable: Boolean = false,
    val providerOperationAuthorized: Boolean = false,
    val runtimeRandomnessReady: Boolean = false,
    val kdfReady: Boolean = false,
    val secureStorageReady: Boolean = false,
    val creationReady: Boolean = false,
    val unlockReady: Boolean = false,
    val activeSessionReady: Boolean = false,
    val persistenceReady: Boolean = false,
    val vaultStorageAvailable: Boolean = false,
    val mainnetReady: Boolean = false,
) {
    override fun toString(): String =
        "SkaldVaultV1AuthorizationReadinessMatrixEvidence(" +
            "policyId=$policyId, " +
            "policyVersion=$policyVersion, " +
            "source=$source, " +
            "status=$status, " +
            "capabilityRows=${capabilityRows.size}, " +
            "blockers=${blockers.size}, " +
            "boundaries=${blockingBoundaries.size}, " +
            "policyToken:<redacted>" +
            ")"
}

sealed class SkaldVaultV1AuthorizationReadinessMatrixResult<out T> {
    data class Blocked<out T>(
        val value: T,
    ) : SkaldVaultV1AuthorizationReadinessMatrixResult<T>() {
        override fun toString(): String =
            "SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked(value=<redacted-matrix-evidence>)"
    }
}

object SkaldVaultV1AuthorizationReadinessMatrixPolicy :
    SkaldVaultV1AuthorizationReadinessMatrixBoundary {
    const val POLICY_ID = "skald-vault-v1-authorization-readiness-matrix-v1"
    const val POLICY_VERSION = 1

    override fun evaluate(
        request: SkaldVaultV1AuthorizationReadinessMatrixRequest,
    ): SkaldVaultV1AuthorizationReadinessMatrixResult<SkaldVaultV1AuthorizationReadinessMatrixEvidence> {
        val rows = rowsFor(request.capabilityId)
        return SkaldVaultV1AuthorizationReadinessMatrixResult.Blocked(
            SkaldVaultV1AuthorizationReadinessMatrixEvidence(
                policyId = POLICY_ID,
                policyVersion = POLICY_VERSION,
                source = request.source,
                status = SkaldVaultV1AuthorizationReadinessMatrixStatus.BlockedFailClosed,
                capability = SkaldVaultV1AuthorizationReadinessMatrixCapability.StillDisabled,
                capabilityRows = rows,
                blockers = rows.flatMap { it.blockerCategories }.toSet(),
                blockingBoundaries = rows.flatMap { it.blockingBoundaries }.toSet(),
                requiredFutureEvidence = rows.flatMap { it.requiredFutureEvidence }.toSet(),
                policyTokenEvidence = SkaldVaultV1AuthorizationReadinessMatrixPolicyToken.redacted(
                    policyId = POLICY_ID,
                    capabilityId = request.capabilityId,
                ),
                providerSelectionEvidenceConsumed = request.providerSelectionEvidenceSupplied,
                providerAcceptanceEvidenceConsumed = request.providerAcceptanceEvidenceSupplied,
                dependencyProbeEvidenceConsumed = request.dependencyProbeEvidenceSupplied,
                encryptedVaultReadinessEvidenceConsumed = request.encryptedVaultReadinessEvidenceSupplied,
            ),
        )
    }

    fun currentPolicySummary(): SkaldVaultV1AuthorizationReadinessPolicySummary =
        SkaldVaultV1AuthorizationReadinessPolicySummary(
            policyId = POLICY_ID,
            policyVersion = POLICY_VERSION,
            statuses = SkaldVaultV1AuthorizationReadinessMatrixStatus.entries.toSet(),
            capabilityIds = SkaldVaultV1AuthorizationReadinessCapabilityId.entries.toSet(),
            boundaryIds = SkaldVaultV1AuthorizationReadinessBoundaryId.entries.toSet(),
            blockerCategories = SkaldVaultV1AuthorizationReadinessBlockerCategory.entries.toSet(),
            requiredFutureEvidence = SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.entries.toSet(),
            redactionClasses = SkaldVaultV1AuthorizationReadinessRedactionClass.entries.toSet(),
            capability = SkaldVaultV1AuthorizationReadinessMatrixCapability.StillDisabled,
            stillDisabled = true,
            evidenceOnly = true,
            allProductionRuntimeCapabilitiesBlocked = true,
        )

    private fun rowsFor(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId?,
    ): List<SkaldVaultV1AuthorizationReadinessCapabilityRow> {
        val rows = currentRows()
        return capabilityId?.let { id -> rows.filter { it.capabilityId == id } } ?: rows
    }

    private fun currentRows(): List<SkaldVaultV1AuthorizationReadinessCapabilityRow> =
        listOf(
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.ProductionProviderSelectability,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.ProviderSelectionBoundary,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.ProductionProviderAcceptanceContract,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.DependencyProbe,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledProviderSelection,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProductionProviderSelectableFalse,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderImplementationMissing,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderKatsNotApproved,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ProductionProviderImplementationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ProviderKatsApproved,
                ),
                flags = setOf("providerSelectable", "productionProviderSelectable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.ProviderOperationExecution,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.ProviderOperationAuthorizationBoundary,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.DisabledProviderFacade,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledProviderSelection,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ProviderOperationAuthorizationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ProductionProviderImplementationApproved,
                ),
                flags = setOf("providerOperationAuthorized"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.RuntimeRandomnessAuthorization,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.RuntimeRandomnessAuthorizationBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.RuntimeRandomnessAuthorizationBlocked,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.RuntimeRandomnessAuthorizationApproved,
                ),
                flags = setOf("runtimeRandomnessReady"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.KdfCalibrationFinalParameterApproval,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.KdfCalibrationAuthorizationBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.KdfCalibrationAuthorizationBlocked,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.KdfCalibrationFinalParametersApproved,
                ),
                flags = setOf("kdfReady"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.SecureSecretStorage,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.SecureStorageAuthorizationBoundary,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.SecureStorageBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureSecretStorageUnavailable,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.SecureStorageAuthorizationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.SecureSecretStorageAvailable,
                ),
                flags = setOf("secureStorageReady", "secureSecretStorageAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.SecureMetadataStorage,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.SecureStorageAuthorizationBoundary,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.SecureMetadataBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureMetadataStorageUnavailable,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.SecureStorageAuthorizationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.SecureMetadataStorageAvailable,
                ),
                flags = setOf("secureStorageReady", "secureMetadataStorageAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.VaultCreation,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.CreationAuthorizationBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PassphraseInputBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.RuntimeRandomnessAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.CreationAuthorizationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PassphraseInputAndRetryApproved,
                ),
                flags = setOf("creationReady"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.VaultUnlock,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.UnlockAuthorizationBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PassphraseInputBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.KdfCalibrationAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ProviderOperationAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.SecureStorageAuthorizationBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.LockSessionLifecycleUnavailable,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.UnlockAuthorizationApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PassphraseInputAndRetryApproved,
                ),
                flags = setOf("unlockReady"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.ActiveSession,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.LockSessionLifecycleBoundary,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.UnlockAuthorizationBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.LockSessionLifecycleUnavailable,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.ClearWipeStrategyModelOnly,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.LockSessionLifecycleApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.UnlockAuthorizationApproved,
                ),
                flags = setOf("activeSessionReady"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.VaultPersistence,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.PersistenceReadinessGate,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.DisabledStorageServiceFacade,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.StorageSafetyPreflightBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledStorageServiceFacade,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.StorageSafetyPreflightModelOnly,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PersistenceReadinessApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.StorageServiceImplementedAndApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.StorageSafetyPreflightApproved,
                ),
                flags = setOf("persistenceReady"),
            ),
            storageRow(SkaldVaultV1AuthorizationReadinessCapabilityId.EncryptedLocalVaultStorage, "vaultStorageAvailable"),
            storageRow(SkaldVaultV1AuthorizationReadinessCapabilityId.ManifestReadWrite, "manifestReadWriteAvailable"),
            storageRow(SkaldVaultV1AuthorizationReadinessCapabilityId.StorageIndexReadWrite, "storageIndexReadWriteAvailable"),
            storageRow(SkaldVaultV1AuthorizationReadinessCapabilityId.RecordReadWrite, "recordReadWriteAvailable"),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.AtomicWrite,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.EncryptedVaultReadinessPolicy,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.PersistenceReadinessGate,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledStorageServiceFacade,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.AtomicWriteCrashRecoveryApproved,
                ),
                flags = setOf("atomicWriteAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.CrashRecovery,
                boundaries = setOf(
                    SkaldVaultV1AuthorizationReadinessBoundaryId.EncryptedVaultReadinessPolicy,
                    SkaldVaultV1AuthorizationReadinessBoundaryId.MigrationCorruptionBoundary,
                ),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.MigrationCorruptionModelOnly,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.AtomicWriteCrashRecoveryApproved,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.MigrationCorruptionRuntimeApproved,
                ),
                flags = setOf("crashRecoveryAvailable"),
            ),
            migrationRow(SkaldVaultV1AuthorizationReadinessCapabilityId.Migration, "migrationAvailable"),
            migrationRow(SkaldVaultV1AuthorizationReadinessCapabilityId.CorruptionRecovery, "corruptionRecoveryAvailable"),
            migrationRow(SkaldVaultV1AuthorizationReadinessCapabilityId.RollbackProtection, "rollbackProtectionAvailable"),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.ClearWipeImplementation,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.ClearWipeStrategyBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.ClearWipeStrategyModelOnly),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ClearWipeRuntimeApproved,
                ),
                flags = setOf("clearWipeAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.RedactionSafeDiagnostics,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.RedactionLeakageBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.RedactionLeakageModelOnly),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.RedactionLeakageRuntimeApproved,
                ),
                flags = setOf("redactionSafeDiagnosticsAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.PassphraseInput,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.PassphrasePolicyBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.PassphraseInputBlocked),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PassphraseInputAndRetryApproved,
                ),
                flags = setOf("passphraseInputAccepted"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.PassphraseRetryThrottle,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.PassphrasePolicyBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.PassphraseInputBlocked),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PassphraseInputAndRetryApproved,
                ),
                flags = setOf("passphraseRetryThrottleAvailable"),
            ),
            androidRow(
                SkaldVaultV1AuthorizationReadinessCapabilityId.AndroidAppPrivateStorageReadiness,
                "androidAppPrivateStorageReady",
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PlatformRootPathSafetyApproved,
            ),
            androidRow(
                SkaldVaultV1AuthorizationReadinessCapabilityId.AndroidLifecycleReadiness,
                "androidLifecycleReady",
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.AndroidLifecycleApproved,
            ),
            androidRow(
                SkaldVaultV1AuthorizationReadinessCapabilityId.AndroidKeystoreOptionalWrapping,
                "androidKeystoreWrappingAvailable",
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.AndroidKeystoreWrapperReviewApproved,
            ),
            linuxRow(
                SkaldVaultV1AuthorizationReadinessCapabilityId.LinuxRootReadiness,
                "linuxRootReady",
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PlatformRootPathSafetyApproved,
            ),
            linuxRow(
                SkaldVaultV1AuthorizationReadinessCapabilityId.LinuxOptionalKeyWrapping,
                "linuxOptionalWrappingAvailable",
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.LinuxOptionalWrapperReviewApproved,
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.BdkPersistence,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.BdkBoundary),
                blockers = setOf(
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.BdkPersistenceNotReviewed,
                    SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
                ),
                futureEvidence = setOf(
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.BdkPersistenceReviewed,
                    SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PersistenceReadinessApproved,
                ),
                flags = setOf("bdkPersistenceAvailable"),
            ),
            walletRow(SkaldVaultV1AuthorizationReadinessCapabilityId.WalletSync, "walletSyncAvailable"),
            walletRow(SkaldVaultV1AuthorizationReadinessCapabilityId.Signing, "signingAvailable"),
            walletRow(SkaldVaultV1AuthorizationReadinessCapabilityId.Broadcasting, "broadcastingAvailable"),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.TorTransport,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.WalletOutOfScopeBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.WalletSyncSigningOutOfScope),
                futureEvidence = setOf(SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.TorNostrReviewed),
                flags = setOf("torTransportAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.NostrParsing,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.WalletOutOfScopeBoundary),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.WalletSyncSigningOutOfScope),
                futureEvidence = setOf(SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.TorNostrReviewed),
                flags = setOf("nostrParsingAvailable"),
            ),
            row(
                capabilityId = SkaldVaultV1AuthorizationReadinessCapabilityId.Mainnet,
                statuses = baseStatuses + SkaldVaultV1AuthorizationReadinessMatrixStatus.DisabledByPolicy,
                boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.MainnetPolicy),
                blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.MainnetDisabled),
                futureEvidence = setOf(SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.MainnetReleaseApproved),
                flags = setOf("mainnetReady", "mainnetAvailable"),
            ),
        )

    private fun storageRow(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        flag: String,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        row(
            capabilityId = capabilityId,
            boundaries = setOf(
                SkaldVaultV1AuthorizationReadinessBoundaryId.DisabledStorageServiceFacade,
                SkaldVaultV1AuthorizationReadinessBoundaryId.StorageSafetyPreflightBoundary,
                SkaldVaultV1AuthorizationReadinessBoundaryId.PlatformPathConstructionBoundary,
            ),
            blockers = setOf(
                SkaldVaultV1AuthorizationReadinessBlockerCategory.DisabledStorageServiceFacade,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.StorageSafetyPreflightModelOnly,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.RootPathEvidenceModelOnly,
            ),
            futureEvidence = setOf(
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.StorageServiceImplementedAndApproved,
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.StorageSafetyPreflightApproved,
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.ManifestStorageIndexRecordImplementationApproved,
            ),
            flags = setOf(flag),
        )

    private fun migrationRow(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        flag: String,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        row(
            capabilityId = capabilityId,
            boundaries = setOf(SkaldVaultV1AuthorizationReadinessBoundaryId.MigrationCorruptionBoundary),
            blockers = setOf(SkaldVaultV1AuthorizationReadinessBlockerCategory.MigrationCorruptionModelOnly),
            futureEvidence = setOf(
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.MigrationCorruptionRuntimeApproved,
            ),
            flags = setOf(flag),
        )

    private fun androidRow(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        flag: String,
        futureEvidence: SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        row(
            capabilityId = capabilityId,
            boundaries = setOf(
                SkaldVaultV1AuthorizationReadinessBoundaryId.AndroidPolicy,
                SkaldVaultV1AuthorizationReadinessBoundaryId.PlatformRootResolverBoundary,
                SkaldVaultV1AuthorizationReadinessBoundaryId.PlatformPathConstructionBoundary,
            ),
            blockers = setOf(
                SkaldVaultV1AuthorizationReadinessBlockerCategory.RootPathEvidenceModelOnly,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.AndroidLifecycleReviewMissing,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.AndroidKeystoreWrappingReviewMissing,
            ),
            futureEvidence = setOf(futureEvidence),
            flags = setOf(flag),
            redactionClass = SkaldVaultV1AuthorizationReadinessRedactionClass.PlatformEvidenceOnly,
        )

    private fun linuxRow(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        flag: String,
        futureEvidence: SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        row(
            capabilityId = capabilityId,
            boundaries = setOf(
                SkaldVaultV1AuthorizationReadinessBoundaryId.LinuxPolicy,
                SkaldVaultV1AuthorizationReadinessBoundaryId.LinuxRootResolutionPolicy,
                SkaldVaultV1AuthorizationReadinessBoundaryId.LinuxCustomRootValidationPolicy,
                SkaldVaultV1AuthorizationReadinessBoundaryId.PlatformRootSettingsPolicy,
            ),
            blockers = setOf(
                SkaldVaultV1AuthorizationReadinessBlockerCategory.RootPathEvidenceModelOnly,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.SettingsRootUsabilityReviewMissing,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.LinuxLifecycleReviewMissing,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.LinuxOptionalWrappingReviewMissing,
            ),
            futureEvidence = setOf(futureEvidence),
            flags = setOf(flag),
            redactionClass = SkaldVaultV1AuthorizationReadinessRedactionClass.PlatformEvidenceOnly,
        )

    private fun walletRow(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        flag: String,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        row(
            capabilityId = capabilityId,
            boundaries = setOf(
                SkaldVaultV1AuthorizationReadinessBoundaryId.WalletOutOfScopeBoundary,
                SkaldVaultV1AuthorizationReadinessBoundaryId.BdkBoundary,
            ),
            blockers = setOf(
                SkaldVaultV1AuthorizationReadinessBlockerCategory.WalletSyncSigningOutOfScope,
                SkaldVaultV1AuthorizationReadinessBlockerCategory.PersistenceReadinessBlocked,
            ),
            futureEvidence = setOf(
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.WalletSyncSigningBroadcastingReviewed,
                SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence.PersistenceReadinessApproved,
            ),
            flags = setOf(flag),
        )

    private fun row(
        capabilityId: SkaldVaultV1AuthorizationReadinessCapabilityId,
        statuses: Set<SkaldVaultV1AuthorizationReadinessMatrixStatus> = baseStatuses,
        boundaries: Set<SkaldVaultV1AuthorizationReadinessBoundaryId>,
        blockers: Set<SkaldVaultV1AuthorizationReadinessBlockerCategory>,
        futureEvidence: Set<SkaldVaultV1AuthorizationReadinessRequiredFutureEvidence>,
        flags: Set<String>,
        redactionClass: SkaldVaultV1AuthorizationReadinessRedactionClass =
            SkaldVaultV1AuthorizationReadinessRedactionClass.BoundaryAndBlockerClassesOnly,
    ): SkaldVaultV1AuthorizationReadinessCapabilityRow =
        SkaldVaultV1AuthorizationReadinessCapabilityRow(
            capabilityId = capabilityId,
            currentStatuses = statuses,
            blockingBoundaries = boundaries,
            blockerCategories = blockers + baseOverrideBlockers,
            requiredFutureEvidence = futureEvidence,
            prohibitedAccidentalReadinessFlags = flags,
            userConsentCanOverride = false,
            warningOnlyEvidenceCanAuthorize = false,
            testOnlyEvidenceCanAuthorizeProduction = false,
            mainnetAllowed = false,
            currentlyReady = false,
            runtimeAvailable = false,
            redactionClass = redactionClass,
        )

    private val baseStatuses: Set<SkaldVaultV1AuthorizationReadinessMatrixStatus> = setOf(
        SkaldVaultV1AuthorizationReadinessMatrixStatus.BlockedFailClosed,
        SkaldVaultV1AuthorizationReadinessMatrixStatus.EvidenceOnly,
        SkaldVaultV1AuthorizationReadinessMatrixStatus.FutureReviewRequired,
        SkaldVaultV1AuthorizationReadinessMatrixStatus.WarningOnlyCannotAuthorize,
        SkaldVaultV1AuthorizationReadinessMatrixStatus.UserConsentCannotOverride,
        SkaldVaultV1AuthorizationReadinessMatrixStatus.TestOnlyRejectedForProduction,
    )

    private val baseOverrideBlockers: Set<SkaldVaultV1AuthorizationReadinessBlockerCategory> = setOf(
        SkaldVaultV1AuthorizationReadinessBlockerCategory.WarningOnlyEvidenceCannotAuthorize,
        SkaldVaultV1AuthorizationReadinessBlockerCategory.UserConsentCannotOverride,
        SkaldVaultV1AuthorizationReadinessBlockerCategory.TestOnlyEvidenceRejectedForProduction,
        SkaldVaultV1AuthorizationReadinessBlockerCategory.MainnetDisabled,
    )
}
