package com.libertasprimordium.skald.security

enum class DesktopTestOnlyVaultCryptoProviderExecutionMode(
    val providerLevelKatExecutionAllowed: Boolean,
    val providerOperationsAllowed: Boolean,
) {
    StaticImplementationOnly(
        providerLevelKatExecutionAllowed = false,
        providerOperationsAllowed = false,
    ),
}

data class DesktopTestOnlyVaultCryptoProviderImplementationReport(
    val identity: SkaldVaultV1TestOnlyExecutableProviderIdentity,
    val executionMode: DesktopTestOnlyVaultCryptoProviderExecutionMode,
    val implementsVaultCryptoProviderInTestSource: Boolean,
    val productionImplementationPresent: Boolean,
    val providerLevelKatExecutedInThisBranch: Boolean,
    val kdfExecutedInThisBranch: Boolean,
    val aeadExecutedInThisBranch: Boolean,
    val providerOperationExecutedInThisBranch: Boolean,
    val providerSelectionEnabled: Boolean,
    val productionProviderSelectable: Boolean,
    val providerRegistryEntryPresent: Boolean,
    val providerFactoryPresent: Boolean,
    val providerDispatcherPresent: Boolean,
    val executorTargetPresent: Boolean,
) {
    override fun toString(): String =
        "DesktopTestOnlyVaultCryptoProviderImplementationReport(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_IMPLEMENTATION_ONLY, PUBLIC_KAT_SCOPE_ONLY, " +
            "NO_PROVIDER_LEVEL_KAT_EXECUTION, NO_PROVIDER_SELECTION" +
            ")"
}

class DesktopTestOnlyVaultCryptoProvider(
    val identity: SkaldVaultV1TestOnlyExecutableProviderIdentity =
        SkaldVaultV1TestOnlyExecutableProviderIdentityPolicy.currentTestOnlyExecutableProviderIdentity(),
    val executionMode: DesktopTestOnlyVaultCryptoProviderExecutionMode =
        DesktopTestOnlyVaultCryptoProviderExecutionMode.StaticImplementationOnly,
    private val disabledProvider: DisabledVaultCryptoProvider = DisabledVaultCryptoProvider(),
) : VaultCryptoProvider {
    override val statusReport: VaultCryptoProviderStatusReport =
        commonDisabledVaultCryptoProviderStatus()

    val implementationReport: DesktopTestOnlyVaultCryptoProviderImplementationReport =
        DesktopTestOnlyVaultCryptoProviderImplementationReport(
            identity = identity,
            executionMode = executionMode,
            implementsVaultCryptoProviderInTestSource = true,
            productionImplementationPresent = identity.productionImplementationPresent,
            providerLevelKatExecutedInThisBranch = identity.providerLevelKatExecutedInThisBranch,
            kdfExecutedInThisBranch = identity.kdfExecutedInThisBranch,
            aeadExecutedInThisBranch = identity.aeadExecutedInThisBranch,
            providerOperationExecutedInThisBranch = false,
            providerSelectionEnabled = identity.providerSelectionEnabled,
            productionProviderSelectable = identity.productionProviderSelectable,
            providerRegistryEntryPresent = identity.providerRegistryEntryPresent,
            providerFactoryPresent = identity.providerFactoryPresent,
            providerDispatcherPresent = identity.providerDispatcherPresent,
            executorTargetPresent = identity.executorTargetPresent,
        )

    val providerLevelKatExecutionEnabled: Boolean
        get() = executionMode.providerLevelKatExecutionAllowed

    val providerOperationsEnabled: Boolean
        get() = executionMode.providerOperationsAllowed

    override fun deriveKey(
        request: VaultCryptoKdfRequest,
    ): VaultCryptoProviderResult<VaultCryptoDerivedKeyHandle> =
        disabledProvider.deriveKey(request)

    override fun encryptRecord(
        request: VaultCryptoAeadEncryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoCiphertextHandle> =
        disabledProvider.encryptRecord(request)

    override fun decryptRecord(
        request: VaultCryptoAeadDecryptRequest,
    ): VaultCryptoProviderResult<VaultCryptoPlaintextHandle> =
        disabledProvider.decryptRecord(request)

    override fun generateKey(
        request: VaultCryptoKeyGenerationRequest,
    ): VaultCryptoProviderResult<VaultCryptoGeneratedKeyHandle> =
        disabledProvider.generateKey(request)

    override fun storeKeyset(
        request: VaultCryptoKeysetStorageRequest,
    ): VaultCryptoProviderResult<VaultCryptoKeysetHandle> =
        disabledProvider.storeKeyset(request)

    override fun validateKat(
        request: VaultCryptoProviderKatRequest,
    ): VaultCryptoProviderResult<VaultCryptoProviderKatEvidence> =
        disabledProvider.validateKat(request)

    override fun toString(): String =
        "DesktopTestOnlyVaultCryptoProvider(" +
            "REDACTED, TEST_SOURCE_ONLY, PROVIDER_IMPLEMENTATION_ONLY, STATIC_ONLY, " +
            "NO_PROVIDER_LEVEL_KAT_EXECUTION" +
            ")"
}
